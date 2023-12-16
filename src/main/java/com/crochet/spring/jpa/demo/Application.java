package com.crochet.spring.jpa.demo;

import com.crochet.spring.jpa.demo.payload.request.PayPalOrderRequest;
import com.crochet.spring.jpa.demo.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private PayPalService paypalService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PayPalOrderRequest request = PayPalOrderRequest.builder()
                .intent("CAPTURE")
                .purchase_units(List.of(PayPalOrderRequest.PurchaseUnit.builder()
                                .amount(PayPalOrderRequest.PurchaseUnit.PayPalAmount.builder()
                                        .currency_code("USD")
                                        .value("100.00")
                                        .build())
                        .build()))
                .build();
        var paypalResponse = paypalService.createProduct(request);
    }
}
