package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.dto.paypal.OrderDTO;
import com.crochet.spring.jpa.demo.dto.paypal.OrderResponseDTO;
import com.crochet.spring.jpa.demo.dto.paypal.PaymentCaptureResponseDTO;
import com.crochet.spring.jpa.demo.model.OrderPattern;
import com.crochet.spring.jpa.demo.model.OrderPatternDetail;
import com.crochet.spring.jpa.demo.repository.CustomerRepo;
import com.crochet.spring.jpa.demo.repository.OrderPatternDetailRepo;
import com.crochet.spring.jpa.demo.repository.OrderPatternRepo;
import com.crochet.spring.jpa.demo.repository.PatternRepo;
import com.crochet.spring.jpa.demo.service.OrderPatternDetailService;
import com.crochet.spring.jpa.demo.service.PayPalService;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class OrderPatternDetailServiceImpl implements OrderPatternDetailService {
    @Autowired
    private OrderPatternDetailRepo orderPatternDetailRepo;
    @Autowired
    private OrderPatternRepo orderRepo;
    @Autowired
    private PatternRepo patternRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private PayPalService payPalService;
    @Autowired
    private Gson gson;

    @SneakyThrows
    @Transactional
    @Override
    public OrderResponseDTO createPayment(UUID customerId,
                                          UUID patternId) {
        var customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        var pattern = patternRepo.findById(patternId)
                .orElseThrow(() -> new RuntimeException("Pattern not found"));

        var orderDTO = OrderDTO.builder()
                .createPayPalOrderDTO(pattern.getCurrencyCode().getValue(),
                        String.valueOf(pattern.getPrice())).build();

        var content = payPalService.createOrder(orderDTO);
        var orderResponseDTO = gson.fromJson(content, OrderResponseDTO.class);

        var order = OrderPattern.builder()
                .customer(customer)
                .build();
        order = orderRepo.save(order);

        var orderPatternDetail = OrderPatternDetail.builder()
                .orderPattern(order)
                .pattern(pattern)
                .transactionId(orderResponseDTO.getId())
                .status(orderResponseDTO.getStatus())
                .orderDate(Date.from(Instant.now()))
                .build();
        orderPatternDetailRepo.save(orderPatternDetail);

        return orderResponseDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public String processPayPalOrderDetail(String transactionId) {
        var content = payPalService.capturePayment(transactionId);
        var payload = gson.fromJson(content, PaymentCaptureResponseDTO.class);
        var orderPatternDetail = orderPatternDetailRepo.findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Order not existed"));
        orderPatternDetail.setStatus(payload.getStatus());
        orderPatternDetailRepo.save(orderPatternDetail);
        return "Processing complete";
    }
}
