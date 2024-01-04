package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.OrderProduct;
import com.crochet.spring.jpa.demo.model.OrderProductDetail;
import com.crochet.spring.jpa.demo.repository.OrderProductRepo;
import com.crochet.spring.jpa.demo.service.CartService;
import com.crochet.spring.jpa.demo.service.CustomerService;
import com.crochet.spring.jpa.demo.service.OrderProductDetailService;
import com.crochet.spring.jpa.demo.service.OrderProductService;
import com.crochet.spring.jpa.demo.service.ShopService;
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
    private ShopService shopService;

    public void checkOutOrder(UUID customerId) {
        // Dia chi nhan hang
        // Kiem tra xem nguoi dat hang co dia chi chua. Neu chua co thi bat ho phai tao
        // Con co roi thi lay dia chi mac dinh cua ho
        var customer = customerService.getCustomerById(customerId);
        // San pham
    }

    @Transactional
    @Override
    public String processOrder(UUID customerId) {
        // Check if the customer exists or not
        var customer = customerService.getCustomerById(customerId);

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
