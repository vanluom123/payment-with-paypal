package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.dto.CheckoutProductOrderDTO;
import com.crochet.spring.jpa.demo.dto.ContactDTO;
import com.crochet.spring.jpa.demo.dto.ShopDTO;
import com.crochet.spring.jpa.demo.dto.ghn.order.GHNOrderCreationRequest;
import com.crochet.spring.jpa.demo.dto.ghn.order.GHNOrderCreationResponse;
import com.crochet.spring.jpa.demo.mapper.ContactMapper;
import com.crochet.spring.jpa.demo.mapper.CustomerMapper;
import com.crochet.spring.jpa.demo.mapper.ProductMapper;
import com.crochet.spring.jpa.demo.mapper.ShopMapper;
import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.Contact;
import com.crochet.spring.jpa.demo.model.Payment;
import com.crochet.spring.jpa.demo.model.ProductOrder;
import com.crochet.spring.jpa.demo.model.ProductOrderDetail;
import com.crochet.spring.jpa.demo.repository.PaymentRepo;
import com.crochet.spring.jpa.demo.repository.ProductOrderDetailRepo;
import com.crochet.spring.jpa.demo.repository.ProductOrderRepo;
import com.crochet.spring.jpa.demo.service.CartDetailService;
import com.crochet.spring.jpa.demo.service.CartService;
import com.crochet.spring.jpa.demo.service.ContactService;
import com.crochet.spring.jpa.demo.service.CustomerService;
import com.crochet.spring.jpa.demo.service.ProductOrderService;
import com.crochet.spring.jpa.demo.service.ProductService;
import com.crochet.spring.jpa.demo.service.ShopService;
import com.crochet.spring.jpa.demo.service.ghn.GHNService;
import com.crochet.spring.jpa.demo.type.OrderStatus;
import com.crochet.spring.jpa.demo.type.PaymentMethod;
import com.crochet.spring.jpa.demo.type.paypal.PaymentStatus;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {
  @Autowired
  private ProductOrderRepo productOrderRepo;
  @Autowired
  private PaymentRepo paymentRepo;
  @Autowired
  private ProductOrderDetailRepo productOrderDetailRepo;

  @Autowired
  private CustomerService customerService;
  @Autowired
  private CartService cartService;
  @Autowired
  private CartDetailService cartDetailService;
  @Autowired
  private GHNService ghnService;
  @Autowired
  private ContactService contactService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ShopService shopService;

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
  public CheckoutProductOrderDTO checkOutOrder(UUID customerId) {
    var customer = customerService.getCustomerById(customerId);

    Contact contact = contactService.findDefaultContact(customer);
    ShopDTO shop = shopMapper.toDTO(shopService.findShopForProducts(customer));

    var checkoutOrderProduct = CheckoutProductOrderDTO.builder()
        .contact(contactMapper.toDTO(contact))
        .shop(shop)
        .products(productMapper.toDTOs(productService.getProductsFromCart(customer)))
        .customer(customerMapper.toDTO(customer))
        .build();

    return checkoutOrderProduct;
  }

  private GHNOrderCreationRequest buildGHNOrderRequest(CheckoutProductOrderDTO checkoutProductOrderDTO) {
    ShopDTO shop = checkoutProductOrderDTO.getShop();
    ContactDTO contact = checkoutProductOrderDTO.getContact();
    var ghnItems = productMapper.toGHNItems(checkoutProductOrderDTO.getProducts());

    ghnItems.forEach(item ->
        item.setQuantity(cartDetailService.getQuantityByProductId(UUID.fromString(item.getCode())))
    );

    int amount = 0;
    if (checkoutProductOrderDTO.getPaymentMethod() == PaymentMethod.CASH_ON_DELIVERY) {
      amount = ghnItems.stream()
          .mapToInt(item -> item.getQuantity() * item.getPrice())
          .sum();
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
        .note(checkoutProductOrderDTO.getNote())
        .height(12).width(12).length(12).weight(100)
        .codAmount(amount)
        .build();
    return request;
  }

  @Transactional
  @Override
  public String processOrder(CheckoutProductOrderDTO checkoutOrder) {
    if (checkoutOrder.getPayment().getPaymentStatus() != PaymentStatus.CREATED) {
      return "Cannot create order";
    }

    var request = buildGHNOrderRequest(checkoutOrder);
    var ghnOrderJson = ghnService.createOrder(request);
    var ghnOrder = gson.fromJson(ghnOrderJson, GHNOrderCreationResponse.class);
    if (ghnOrder.getCode() != HttpStatus.OK.value() || ghnOrder.getCode() != HttpStatus.CREATED.value()) {
      return "Cannot create shipping";
    }

    // Check if the customer exists or not
    var customer = customerMapper.toEntity(checkoutOrder.getCustomer());

    // Retrieve the list of carts by customer
    Cart cart = cartService.getCartByCustomer(customer);

    // Create order
    ProductOrder productOrder = ProductOrder.builder()
        .customer(customer)
        .status(OrderStatus.PENDING)
        .orderDate(Date.from(Instant.now()))
        .totalAmount(cart.getTotalAmount())
        .build();
    productOrder = productOrderRepo.save(productOrder);

    if (checkoutOrder.getPaymentMethod() == PaymentMethod.PAYPAL) {
      Payment payment = Payment.builder()
          .transactionId(checkoutOrder.getPayment().getTransactionId())
          .amount(cart.getTotalAmount())
          .paymentStatus(checkoutOrder.getPayment().getPaymentStatus())
          .paymentDate(Date.from(Instant.now()))
          .productOrder(productOrder)
          .build();
      paymentRepo.save(payment);
    }

    // Make a copy
    ProductOrder finalProductOrder = productOrder;
    // Create order detail
    List<ProductOrderDetail> orderDetails = cart.getCartDetails()
        .stream()
        .map(cd -> ProductOrderDetail.builder()
            .productOrder(finalProductOrder)
            .product(cd.getProduct())
            .quantity(cd.getQuantity())
            .build())
        .collect(Collectors.toList());

    // Save all order details in bulk
    productOrderDetailRepo.saveAll(orderDetails);

    // Delete all carts
    cartService.deleteCart(cart);

    return "Order success";
  }
}
