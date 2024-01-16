package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.PatternOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatternOrderRepo extends JpaRepository<PatternOrder, UUID> {
  Optional<PatternOrder> findOrderByCustomerId(UUID customerId);
}