package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.dto.paypal.OrderDTO;
import com.crochet.spring.jpa.demo.dto.paypal.OrderResponseDTO;
import com.crochet.spring.jpa.demo.dto.paypal.PaymentCaptureResponseDTO;
import com.crochet.spring.jpa.demo.model.PatternOrder;
import com.crochet.spring.jpa.demo.model.PatternOrderDetail;
import com.crochet.spring.jpa.demo.repository.CustomerRepo;
import com.crochet.spring.jpa.demo.repository.PatternOrderDetailRepo;
import com.crochet.spring.jpa.demo.repository.PatternOrderRepo;
import com.crochet.spring.jpa.demo.repository.PatternRepo;
import com.crochet.spring.jpa.demo.service.PatternOrderDetailService;
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
public class PatternOrderDetailServiceImpl implements PatternOrderDetailService {
  @Autowired
  private PatternOrderDetailRepo patternOrderDetailRepo;
  @Autowired
  private PatternOrderRepo patternOrderRepo;
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
            String.valueOf(pattern.getPrice()),
            "/api/order-pattern-detail/success")
        .build();

    var content = payPalService.createOrder(orderDTO);
    var orderResponseDTO = gson.fromJson(content, OrderResponseDTO.class);

    var order = PatternOrder.builder()
        .customer(customer)
        .build();
    order = patternOrderRepo.save(order);

    var orderPatternDetail = PatternOrderDetail.builder()
        .patternOrder(order)
        .pattern(pattern)
        .transactionId(orderResponseDTO.getId())
        .status(orderResponseDTO.getStatus())
        .orderDate(Date.from(Instant.now()))
        .build();
    patternOrderDetailRepo.save(orderPatternDetail);

    return orderResponseDTO;
  }

  @SneakyThrows
  @Transactional
  @Override
  public String processPayPalOrderDetail(String transactionId) {
    var content = payPalService.capturePayment(transactionId);
    var payload = gson.fromJson(content, PaymentCaptureResponseDTO.class);
    var orderPatternDetail = patternOrderDetailRepo.findByTransactionId(transactionId)
        .orElseThrow(() -> new RuntimeException("Order not existed"));
    orderPatternDetail.setStatus(payload.getStatus());
    patternOrderDetailRepo.save(orderPatternDetail);
    return "Processing complete";
  }
}
