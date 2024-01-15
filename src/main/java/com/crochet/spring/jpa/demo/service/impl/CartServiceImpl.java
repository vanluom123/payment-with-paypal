package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.common.MessageConstant;
import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.CartDetail;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.repository.CartRepo;
import com.crochet.spring.jpa.demo.service.CartService;
import com.crochet.spring.jpa.demo.service.CustomerService;
import com.crochet.spring.jpa.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
  @Autowired
  private CartRepo cartRepo;
  @Autowired
  private ProductService productService;
  @Autowired
  private CustomerService customerService;

  @Transactional
  @Override
  public String addProductToCart(UUID customerId, UUID productId, int quantity) {
    // Check customer and product
    var customer = customerService.getCustomerById(customerId);
    var product = productService.getById(productId);

    // Get or create cart
    Cart cart = customer.getCart();
    if (cart == null) {
      cart = Cart.builder()
          .customer(customer)
          .totalAmount(0.0)  // Initialize totalAmount to 0
          .cartDetails(new HashSet<>())  // Initialize cart details list
          .build();
    }

    // Check if the product is already in the cart
    CartDetail cartDetail = findCartDetailByProductId(cart, productId);

    if (cartDetail != null) {
      // Product already exists in cart, update quantity
      int newQuantity = cartDetail.getQuantity() + quantity;
      cartDetail.setQuantity(newQuantity);
    } else {
      // Product does not exist in cart, create new CartDetail
      cartDetail = new CartDetail(quantity, product, cart);
      cart.getCartDetails().add(cartDetail);
    }

    // Update totalAmount in the cart
    double newTotalAmount = cart.getTotalAmount() + (quantity * product.getPrice());
    cart.setTotalAmount(newTotalAmount);

    // Save changes to the database
    cartRepo.save(cart);

    return "Added to cart";
  }

  @Override
  public Cart getCartByCustomer(Customer customer) {
    return cartRepo.findCartByCustomer(customer)
        .orElseThrow(() -> new RuntimeException(MessageConstant.CART_EMPTY));
  }

  @Transactional
  @Override
  public void deleteCart(Cart cart) {
    cartRepo.delete(cart);
  }

  private CartDetail findCartDetailByProductId(Cart cart, UUID productId) {
    return cart.getCartDetails().stream()
        .filter(cd -> cd.getProduct().getId().equals(productId))
        .findFirst()
        .orElse(null);
  }
}