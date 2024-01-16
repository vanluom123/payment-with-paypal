package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartDetailRepo extends JpaRepository<CartDetail, UUID> {
  @Query("""
          select cd.quantity from CartDetail cd
          where cd.product.id = ?1
      """)
  Integer findQuantityByProductId(UUID productId);
}