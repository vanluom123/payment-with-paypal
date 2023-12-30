package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.OrderProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderProductDetailRepo extends JpaRepository<OrderProductDetail, UUID> {
}