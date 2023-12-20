package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatternRepo extends JpaRepository<Pattern, UUID> {
    @Query("select p from Pattern p " +
            "join fetch p.orderPatternDetails opd " +
            "join fetch opd.order o " +
            "join fetch o.customer c " +
            "where p.id = ?2 and c.id = ?1 and opd.status = 'COMPLETED'")
    Optional<Pattern> findCompletedPatterns(UUID customerId, UUID patternId);
}