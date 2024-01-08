package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.mapper.ContactMapper;
import com.crochet.spring.jpa.demo.mapper.CustomerMapper;
import com.crochet.spring.jpa.demo.mapper.ProductMapper;
import com.crochet.spring.jpa.demo.mapper.ShopMapper;
import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.Contact;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.model.OrderProduct;
import com.crochet.spring.jpa.demo.model.OrderProductDetail;
import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.payload.dto.ghn.order.GHNCreateOrderRequest;
import com.crochet.spring.jpa.demo.payload.response.CheckoutOrderProductResponse;
import com.crochet.spring.jpa.demo.payload.response.ContactResponse;
import com.crochet.spring.jpa.demo.payload.response.ShopResponse;
import com.crochet.spring.jpa.demo.repository.OrderProductRepo;
import com.crochet.spring.jpa.demo.service.CartService;
import com.crochet.spring.jpa.demo.service.CustomerService;
import com.crochet.spring.jpa.demo.service.OrderProductDetailService;
import com.crochet.spring.jpa.demo.service.OrderProductService;
import com.crochet.spring.jpa.demo.service.ghn.GHNService;
import com.crochet.spring.jpa.demo.type.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderProductServiceImpl implements OrderProductService {
    @Autowired
    private OrderProductRepo orderProductRepo;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderProductDetailService orderProductDetailService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private GHNService ghnService;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CheckoutOrderProductResponse checkOutOrder(UUID customerId) {
        var customer = customerService.getCustomerById(customerId);

        Contact contact = findDefaultContact(customer);
        ShopResponse shop = findShopForProducts(customer);

        var checkoutOrderProduct = CheckoutOrderProductResponse.builder()
                .contact(contactMapper.toResponse(contact))
                .shop(shop)
                .products(productMapper.toResponses(getProductsFromCart(customer)))
                .customer(customerMapper.toResponse(customer))
                .build();

        buildGHNOrderRequest(checkoutOrderProduct);

        return checkoutOrderProduct;
    }

    private Contact findDefaultContact(Customer customer) {
        return customer.getContacts()
                .stream()
                .filter(Contact::isDefault)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Default contact not found"));
    }

    private ShopResponse findShopForProducts(Customer customer) {
        return customer.getCarts()
                .stream()
                .map(Cart::getProduct)
                .map(prod -> shopMapper.toResponse(prod.getShop()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Shop not found"));
    }

    private List<Product> getProductsFromCart(Customer customer) {
        return customer.getCarts()
                .stream()
                .map(Cart::getProduct)
                .toList();
    }

    private GHNCreateOrderRequest buildGHNOrderRequest(CheckoutOrderProductResponse checkoutOrderProductResponse) {
        ShopResponse shop = checkoutOrderProductResponse.getShop();
        ContactResponse contact = checkoutOrderProductResponse.getContact();
        var ghnItems = productMapper.toGHNItems(checkoutOrderProductResponse.getProducts());
        ghnItems.forEach(item -> item.setQuantity(cartService.getQuantityByProductId(item.getCode())));
        GHNCreateOrderRequest request = GHNCreateOrderRequest.builder()
                .fromName(shop.getShopName())
                .fromPhone(shop.getPhone())
                .fromAddress(shop.getAddress())
                .fromWardName(shop.getWardName())
                .fromDistrictName(shop.getDistrictName())
                .fromProvinceName(shop.getProvinceName())
                .toName(contact.getName())
                .toPhone(contact.getPhone())
                .toAddress(contact.getAddress())
                .toWardCode(contact.getWardCode())
                .toDistrictId(contact.getDistrictID())
                .items(ghnItems)
                .note(checkoutOrderProductResponse.getNote())
                .height(12).width(12).length(12).weight(100)
                .build();
        return request;
    }

    @Transactional
    @Override
    public String processOrder(CheckoutOrderProductResponse checkoutOrder) {
        var request = buildGHNOrderRequest(checkoutOrder);
        ghnService.createOrder(request);

        // Check if the customer exists or not
        var customer = customerMapper.toEntity(checkoutOrder.getCustomer());

        // Retrieve the list of carts by customer
        List<Cart> carts = cartService.getAllCartByCustomer(customer);
        if (ObjectUtils.isEmpty(carts)) {
            throw new RuntimeException("Cart not existed");
        }

        // Get the amount by multiplying with the quantity and then add it up
        double totalAmount = carts.stream()
                .mapToDouble(cart -> cart.getProduct().getPrice() * cart.getQuantity())
                .sum();

        // Create order
        OrderProduct orderProduct = OrderProduct.builder()
                .customer(customer)
                .status(OrderStatus.PENDING)
                .orderDate(Date.from(Instant.now()))
                .totalAmount(totalAmount)
                .build();
        orderProduct = orderProductRepo.save(orderProduct);

        // Make a copy
        OrderProduct finalOrderProduct = orderProduct;
        // Create order detail
        List<OrderProductDetail> orderDetails = carts.stream()
                .map(cart -> OrderProductDetail.builder()
                        .orderProduct(finalOrderProduct)
                        .product(cart.getProduct())
                        .quantity(cart.getQuantity())
                        .build())
                .collect(Collectors.toList());
        orderProductDetailService.saveAll(orderDetails);
        cartService.deleteAll(carts);
        return "Order success";
    }
}
