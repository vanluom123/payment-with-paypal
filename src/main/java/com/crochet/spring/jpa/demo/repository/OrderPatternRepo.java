package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.OrderPatternDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderPatternRepo extends JpaRepository<OrderPatternDetail, UUID> {
    Optional<OrderPatternDetail> findByTransactionId(String transactionId);
}