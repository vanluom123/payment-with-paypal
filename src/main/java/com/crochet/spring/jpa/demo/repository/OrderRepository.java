package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findOrderByCustomerId(UUID customerId);
}