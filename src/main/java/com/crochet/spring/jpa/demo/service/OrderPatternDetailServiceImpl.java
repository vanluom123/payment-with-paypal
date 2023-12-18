package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.OrderPatternDetail;
import com.crochet.spring.jpa.demo.payload.request.paypal.PayPalOrderRequest;
import com.crochet.spring.jpa.demo.payload.response.paypal.PaymentResponse;
import com.crochet.spring.jpa.demo.repository.OrderRepository;
import com.crochet.spring.jpa.demo.repository.PatternRepo;
import com.crochet.spring.jpa.demo.repository.OrderPatternDetailRepo;
import com.crochet.spring.jpa.demo.service.contact.PayPalService;
import com.crochet.spring.jpa.demo.service.contact.OrderPatternDetailService;
import com.crochet.spring.jpa.demo.type.OrderStatus;
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
    private OrderRepository orderRepo;

    @Autowired
    private PatternRepo patternRepo;

    @Autowired
    private PayPalService payPalService;

    @Transactional
    @Override
    public String createPayment(String orderId,
                                String patternId,
                                PayPalOrderRequest request) {
        var order = orderRepo.findById(UUID.fromString(orderId))
                .orElseThrow(() -> new RuntimeException("Order not found"));
        var pattern = patternRepo.findById(UUID.fromString(patternId))
                .orElseThrow(() -> new RuntimeException("Pattern not found"));

        var response = payPalService.createOrder(request);
        var orderPatternDetail = OrderPatternDetail.builder()
                .order(order)
                .pattern(pattern)
                .transactionId(response.getId())
                .status(OrderStatus.valueOf(response.getStatus()))
                .orderDate(Date.from(Instant.now()))
                .build();
        orderPatternDetailRepo.save(orderPatternDetail);

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
        var orderPatternDetail = orderPatternDetailRepo.findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Order not existed"));
        orderPatternDetail.setStatus(OrderStatus.valueOf(response.getStatus()));
        orderPatternDetailRepo.save(orderPatternDetail);
        return "Processing complete";
    }
}
