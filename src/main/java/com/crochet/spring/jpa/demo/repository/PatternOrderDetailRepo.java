package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.PatternOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatternOrderDetailRepo extends JpaRepository<PatternOrderDetail, UUID> {
  Optional<PatternOrderDetail> findByTransactionId(String transactionId);
}