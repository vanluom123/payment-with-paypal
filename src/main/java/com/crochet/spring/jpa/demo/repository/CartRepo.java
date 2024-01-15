package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepo extends JpaRepository<Cart, UUID> {
  @Transactional
  @Procedure("AddProductToCart")
  void addProductToCart(UUID customerId, UUID productId, int quantity);

  @Transactional
  @Procedure("Proc_PlaceOrder")
  void placeOrder(UUID cusId);

  @Query("select c from Cart c where c.customer = ?1")
  Optional<Cart> findCartByCustomer(Customer cus);
}