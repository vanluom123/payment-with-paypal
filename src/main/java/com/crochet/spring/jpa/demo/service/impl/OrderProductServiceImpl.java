package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.dto.CheckoutOrderProductDTO;
import com.crochet.spring.jpa.demo.dto.ContactDTO;
import com.crochet.spring.jpa.demo.dto.ShopDTO;
import com.crochet.spring.jpa.demo.dto.ghn.order.GHNOrderCreationRequest;
import com.crochet.spring.jpa.demo.dto.paypal.OrderDTO;
import com.crochet.spring.jpa.demo.dto.paypal.OrderResponseDTO;
import com.crochet.spring.jpa.demo.mapper.ContactMapper;
import com.crochet.spring.jpa.demo.mapper.CustomerMapper;
import com.crochet.spring.jpa.demo.mapper.ProductMapper;
import com.crochet.spring.jpa.demo.mapper.ShopMapper;
import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.Contact;
import com.crochet.spring.jpa.demo.model.OrderProduct;
import com.crochet.spring.jpa.demo.model.OrderProductDetail;
import com.crochet.spring.jpa.demo.repository.OrderProductRepo;
import com.crochet.spring.jpa.demo.service.CartService;
import com.crochet.spring.jpa.demo.service.ContactService;
import com.crochet.spring.jpa.demo.service.CurrencyService;
import com.crochet.spring.jpa.demo.service.CustomerService;
import com.crochet.spring.jpa.demo.service.OrderProductDetailService;
import com.crochet.spring.jpa.demo.service.OrderProductService;
import com.crochet.spring.jpa.demo.service.PayPalService;
import com.crochet.spring.jpa.demo.service.ProductService;
import com.crochet.spring.jpa.demo.service.ShopService;
import com.crochet.spring.jpa.demo.service.ghn.GHNService;
import com.crochet.spring.jpa.demo.type.OrderStatus;
import com.crochet.spring.jpa.demo.type.PaymentMethod;
import com.google.gson.Gson;
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
    private GHNService ghnService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private PayPalService payPalService;
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private Gson gson;

    @Override
    public CheckoutOrderProductDTO checkOutOrder(UUID customerId, int paymentMethod) {
        var customer = customerService.getCustomerById(customerId);

        Contact contact = contactService.findDefaultContact(customer);
        ShopDTO shop = shopMapper.toDTO(shopService.findShopForProducts(customer));

        var checkoutOrderProduct = CheckoutOrderProductDTO.builder()
                .contact(contactMapper.toDTO(contact))
                .shop(shop)
                .products(productMapper.toDTOs(productService.getProductsFromCart(customer)))
                .customer(customerMapper.toDTO(customer))
                .paymentMethod(paymentMethod)
                .build();

        return checkoutOrderProduct;
    }

    private GHNOrderCreationRequest buildGHNOrderRequest(CheckoutOrderProductDTO checkoutOrderProductDTO) {
        ShopDTO shop = checkoutOrderProductDTO.getShop();
        ContactDTO contact = checkoutOrderProductDTO.getContact();
        var ghnItems = productMapper.toGHNItems(checkoutOrderProductDTO.getProducts());
        ghnItems.forEach(item -> item.setQuantity(cartService.getQuantityByProductId(item.getCode())));
        int amount = 0;
        if (checkoutOrderProductDTO.getPaymentMethod() == PaymentMethod.PAYMENT_AFTER_RECEIVED.getValue()) {
            amount = ghnItems.stream()
                    .mapToInt(item -> item.getQuantity() * item.getPrice())
                    .sum();
        } else {
            var totalAmount = ghnItems.stream()
                    .mapToInt(item -> item.getQuantity() * item.getPrice())
                    .sum();
            var exchangeRate = currencyService.convertCurrency("VND", "USD");
            var usdRate = exchangeRate.getData().get("USD").getValue();
            var paypalOrder = OrderDTO.builder()
                    .createPayPalOrderDTO("USD", String.valueOf(usdRate * totalAmount))
                    .build();
            var content = payPalService.createOrder(paypalOrder);
            var orderResponseDTO = gson.fromJson(content, OrderResponseDTO.class);
        }
        GHNOrderCreationRequest request = GHNOrderCreationRequest.builder()
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
                .note(checkoutOrderProductDTO.getNote())
                .height(12).width(12).length(12).weight(100)
                .codAmount(amount)
                .build();
        return request;
    }

    @Transactional
    @Override
    public String processOrder(CheckoutOrderProductDTO checkoutOrder) {
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
