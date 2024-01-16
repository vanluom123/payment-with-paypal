package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactRepo extends JpaRepository<Contact, UUID> {
    @Query("select c from Contact c where c.customer.id = ?1")
    Optional<List<Contact>> findByCustomer(UUID customerId);
}