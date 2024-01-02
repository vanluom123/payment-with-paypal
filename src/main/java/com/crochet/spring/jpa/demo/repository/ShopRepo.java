package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShopRepo extends JpaRepository<Shop, UUID> {
}