package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.OrderProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderProductDetailRepo extends JpaRepository<OrderProductDetail, UUID> {
    @Query("select o.quantity from OrderProductDetail o where o.product.id = ?1")
    Integer findByProductId(UUID productId);
}