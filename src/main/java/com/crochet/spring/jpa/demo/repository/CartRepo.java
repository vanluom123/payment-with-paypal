package com.crochet.spring.jpa.demo.repository;

import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Query("select c.product from Cart c" +
            " inner join c.product p on c.product.id = p.id" +
            " where c.customer = ?1")
    List<Product> findProductFromCartByCustomer(Customer customer);

    @Transactional
    @Modifying
    @Query("delete Cart c where c.customer=?1 and c.product=?2")
    void deleteCartByCustomerId(Customer cus, Product prod);

    @Query("select c from Cart c where c.customer = ?1 and c.product = ?2")
    Optional<Cart> findCartByCustomerAndProduct(Customer customer, Product product);

    @Query("select c from Cart c where c.customer = ?1")
    List<Cart> findAllCartByCustomer(Customer cus);
}