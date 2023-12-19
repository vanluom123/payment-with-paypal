package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.Order;
import com.crochet.spring.jpa.demo.model.OrderPatternDetail;
import com.crochet.spring.jpa.demo.payload.dto.CapturePaymentResponseDTO;
import com.crochet.spring.jpa.demo.payload.dto.MoneyDTO;
import com.crochet.spring.jpa.demo.payload.dto.OrderDTO;
import com.crochet.spring.jpa.demo.payload.dto.OrderResponseDTO;
import com.crochet.spring.jpa.demo.payload.dto.PayPalAppContextDTO;
import com.crochet.spring.jpa.demo.payload.dto.PurchaseUnit;
import com.crochet.spring.jpa.demo.repository.CustomerRepository;
import com.crochet.spring.jpa.demo.repository.OrderPatternDetailRepo;
import com.crochet.spring.jpa.demo.repository.OrderRepository;
import com.crochet.spring.jpa.demo.repository.PatternRepo;
import com.crochet.spring.jpa.demo.service.contact.OrderPatternDetailService;
import com.crochet.spring.jpa.demo.service.contact.PayPalService;
import com.crochet.spring.jpa.demo.type.OrderIntent;
import com.crochet.spring.jpa.demo.type.PaymentLandingPage;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.util.ArrayList;
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

    @Autowired
    private Gson gson;

    @SneakyThrows
    @Transactional
    @Override
    public OrderResponseDTO createPayment(String customerId,
                                          String patternId) {
        var customer = customerRepo.findById(UUID.fromString(customerId))
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        var pattern = patternRepo.findById(UUID.fromString(patternId))
                .orElseThrow(() -> new RuntimeException("Pattern not found"));

        var orderDTO = createOrderDTO(pattern.getCurrencyCode().getValue(),
                String.valueOf(pattern.getPrice()));

        var content = payPalService.createOrder(orderDTO);
        var orderResponseDTO = gson.fromJson(content, OrderResponseDTO.class);

        var order = Order.builder()
                .customer(customer)
                .build();
        order = orderRepo.save(order);

        var orderPatternDetail = OrderPatternDetail.builder()
                .order(order)
                .pattern(pattern)
                .transactionId(orderResponseDTO.getId())
                .status(orderResponseDTO.getStatus())
                .orderDate(Date.from(Instant.now()))
                .build();
        orderPatternDetailRepo.save(orderPatternDetail);

        return orderResponseDTO;
    }

    private static OrderDTO createOrderDTO(String currencyCode, String value) {
        String baseUri = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        var appContext = PayPalAppContextDTO.builder()
                .returnUrl(baseUri + "/api/order-pattern-detail/success")
                .brandName("Little Crochet")
                .landingPage(PaymentLandingPage.BILLING)
                .build();
        MoneyDTO moneyDTO = MoneyDTO.builder()
                .currencyCode(currencyCode)
                .value(String.valueOf(value))
                .build();
        PurchaseUnit purchaseUnit = PurchaseUnit.builder()
                .amount(moneyDTO)
                .build();
        List<PurchaseUnit> purchaseUnits = new ArrayList<>();
        purchaseUnits.add(purchaseUnit);
        var orderDTO = OrderDTO.builder()
                .intent(OrderIntent.CAPTURE)
                .applicationContext(appContext)
                .purchaseUnits(purchaseUnits)
                .build();
        return orderDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public String processPayPalOrderDetail(String transactionId) {
        var content = payPalService.capturePayment(transactionId);
        var payload = gson.fromJson(content, CapturePaymentResponseDTO.class);
        var orderPatternDetail = orderPatternDetailRepo.findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Order not existed"));
        orderPatternDetail.setStatus(payload.getStatus());
        orderPatternDetailRepo.save(orderPatternDetail);
        return "Processing complete";
    }
}
