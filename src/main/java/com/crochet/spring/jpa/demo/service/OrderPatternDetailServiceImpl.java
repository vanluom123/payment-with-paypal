package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Order;
import com.crochet.spring.jpa.demo.model.OrderPatternDetail;
import com.crochet.spring.jpa.demo.model.Pattern;
import com.crochet.spring.jpa.demo.payload.request.paypal.PayPalOrderRequest;
import com.crochet.spring.jpa.demo.payload.response.OrderPatternDetailResponse;
import com.crochet.spring.jpa.demo.payload.response.paypal.PaymentResponse;
import com.crochet.spring.jpa.demo.repository.CustomerRepository;
import com.crochet.spring.jpa.demo.repository.OrderPatternDetailRepo;
import com.crochet.spring.jpa.demo.repository.OrderRepository;
import com.crochet.spring.jpa.demo.repository.PatternRepo;
import com.crochet.spring.jpa.demo.service.contact.OrderPatternDetailService;
import com.crochet.spring.jpa.demo.service.contact.PayPalService;
import com.crochet.spring.jpa.demo.type.OrderIntent;
import com.crochet.spring.jpa.demo.type.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
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
    private CustomerRepository customerRepo;

    @Autowired
    private PayPalService payPalService;

    @Transactional
    @Override
    public OrderPatternDetailResponse createPayment(String customerId,
                                                    String patternId) {
        var customer = customerRepo.findById(UUID.fromString(customerId))
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        var pattern = patternRepo.findById(UUID.fromString(patternId))
                .orElseThrow(() -> new RuntimeException("Pattern not found"));

        PayPalOrderRequest request = createPayPalOrderRequest(pattern);
        var response = payPalService.createOrder(request);
        var order = Order.builder()
                .customer(customer)
                .build();
        order = orderRepo.save(order);
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

        return OrderPatternDetailResponse.builder()
                .transactionId(orderPatternDetail.getTransactionId())
                .status(orderPatternDetail.getStatus())
                .orderDate(orderPatternDetail.getOrderDate())
                .approvalLink(approvalLink)
                .build();
    }

    private static PayPalOrderRequest createPayPalOrderRequest(Pattern pattern) {
        PayPalOrderRequest request = new PayPalOrderRequest();
        request.setIntent(OrderIntent.CAPTURE);
        PayPalOrderRequest.PurchaseUnit purchaseUnit = new PayPalOrderRequest.PurchaseUnit();
        PayPalOrderRequest.PurchaseUnit.PayPalAmount amount = new PayPalOrderRequest.PurchaseUnit.PayPalAmount();
        amount.setCurrencyCode("USD");
        amount.setValue(String.valueOf(pattern.getPrice()));
        purchaseUnit.setAmount(amount);
        request.setPurchaseUnits(List.of(purchaseUnit));
        return request;
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
