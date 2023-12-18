package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatternRepo extends JpaRepository<Pattern, UUID> {
}