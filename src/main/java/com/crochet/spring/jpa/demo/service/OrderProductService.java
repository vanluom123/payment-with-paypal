package com.crochet.spring.jpa.demo.service;

import java.util.UUID;

public interface OrderProductService {
    //    @Transactional
    String processOrder(UUID customerId);
}
