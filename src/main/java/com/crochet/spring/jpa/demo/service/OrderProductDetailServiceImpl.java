package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.mapper.OrderMapper;
import com.crochet.spring.jpa.demo.model.Order;
import com.crochet.spring.jpa.demo.model.OrderProductDetail;
import com.crochet.spring.jpa.demo.payload.request.OrderRequest;
import com.crochet.spring.jpa.demo.payload.response.OrderProductDetailResponse;
import com.crochet.spring.jpa.demo.repository.CustomerRepository;
import com.crochet.spring.jpa.demo.repository.OrderProductDetailRepository;
import com.crochet.spring.jpa.demo.repository.OrderRepository;
import com.crochet.spring.jpa.demo.repository.ProductRepository;
import com.crochet.spring.jpa.demo.service.contact.OrderProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Service
public class OrderProductDetailServiceImpl implements OrderProductDetailService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderProductDetailRepository orderDetailRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ProductRepository productRepo;

    @Transactional
    @Override
    public OrderProductDetailResponse orderProduct(OrderRequest request) {
        var customer = customerRepo.findById(UUID.fromString(request.getCustomerId()))
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        var product = productRepo.findById(UUID.fromString(request.getProductId()))
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Create new order
        var order = Order.builder()
                .customer(customer)
                .build();
        order = orderRepo.save(order);

        var orderDetail = OrderProductDetail.builder()
                .order(order)
                .product(product)
                .orderDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
                .quantity(request.getQuantity())
                .price(request.getQuantity() * product.getPrice())
                .build();
        orderDetail = orderDetailRepo.save(orderDetail);
        return OrderMapper.INSTANCE.orderDetailToOrderResult(orderDetail);
    }
}
