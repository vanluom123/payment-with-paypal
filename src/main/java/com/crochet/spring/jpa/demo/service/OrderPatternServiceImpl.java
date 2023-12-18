package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.OrderPattern;
import com.crochet.spring.jpa.demo.payload.request.paypal.PayPalOrderRequest;
import com.crochet.spring.jpa.demo.payload.response.paypal.PaymentResponse;
import com.crochet.spring.jpa.demo.repository.CustomerRepository;
import com.crochet.spring.jpa.demo.repository.PatternRepo;
import com.crochet.spring.jpa.demo.repository.OrderPatternRepo;
import com.crochet.spring.jpa.demo.service.contact.PayPalService;
import com.crochet.spring.jpa.demo.service.contact.OrderPatternService;
import com.crochet.spring.jpa.demo.type.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class OrderPatternServiceImpl implements OrderPatternService {
    @Autowired
    private OrderPatternRepo orderPatternRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private PatternRepo patternRepo;

    @Autowired
    private PayPalService payPalService;

    @Transactional
    @Override
    public String createPayment(String customerId,
                                String patternId,
                                PayPalOrderRequest request) {
        var customer = customerRepo.findById(UUID.fromString(customerId))
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        var pattern = patternRepo.findById(UUID.fromString(patternId))
                .orElseThrow(() -> new RuntimeException("Pattern not found"));

        var response = payPalService.createOrder(request);
        var paymentPattern = OrderPattern.builder()
                .customer(customer)
                .pattern(pattern)
                .transactionId(response.getId())
                .status(OrderStatus.valueOf(response.getStatus()))
                .paymentDate(Date.from(Instant.now()))
                .build();
        orderPatternRepo.save(paymentPattern);

        var approvalLink = response.getLinks()
                .stream()
                .filter(link -> link.getRel().equals("approve"))
                .map(PaymentResponse.Link::getHref)
                .findFirst()
                .orElse(null);

        return approvalLink;
    }

    @Transactional
    @Override
    public String processPayPalOrderDetail(String transactionId) {
        var response = payPalService.getOrderDetail(transactionId);
        var orderPattern = orderPatternRepo.findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Order not existed"));
        orderPattern.setStatus(OrderStatus.valueOf(response.getStatus()));
        orderPatternRepo.save(orderPattern);
        return "Processing complete";
    }
}
