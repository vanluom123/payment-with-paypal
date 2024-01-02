package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.model.*;
import com.crochet.spring.jpa.demo.repository.CartRepo;
import com.crochet.spring.jpa.demo.repository.CustomerRepo;
import com.crochet.spring.jpa.demo.repository.OrderProductDetailRepo;
import com.crochet.spring.jpa.demo.repository.OrderProductRepo;
import com.crochet.spring.jpa.demo.service.CartService;
import com.crochet.spring.jpa.demo.type.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private OrderProductRepo orderProductRepo;
    @Autowired
    private OrderProductDetailRepo orderProductDetailRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String addProductToCart(String customerId, String productId, int quantity) {
        cartRepo.addProductToCart(UUID.fromString(customerId), UUID.fromString(productId), quantity);
        return "Added to cart";
    }

    @Override
    public String placeOrder_2(String cusId) {
        cartRepo.placeOrder(UUID.fromString(cusId));
        return "Order success";
    }

    @Transactional
    @Override
    public String placeOrder(String customerId) {
        // Kiem tra customer co ton tai khong
        UUID cusUUID = UUID.fromString(customerId);
        var customer = customerRepo.findById(cusUUID)
                .orElseThrow(() -> new RuntimeException("Customer not existed"));

        // Lay danh sach cart theo customer
        List<Cart> carts = cartRepo.findAllCartByCustomer(customer);
        if (ObjectUtils.isEmpty(carts)) {
            throw new RuntimeException("Cart not existed");
        }

        // Lay amount nhan cho quantity roi cong lai
        double totalAmount = carts.stream()
                .mapToDouble(cart -> cart.getProduct().getPrice() * cart.getQuantity())
                .sum();

        // tao order
        OrderProduct orderProduct = OrderProduct.builder()
                .customer(customer)
                .status(OrderStatus.PENDING)
                .orderDate(Date.from(Instant.now()))
                .totalAmount(totalAmount)
                .build();
        orderProduct = orderProductRepo.save(orderProduct);

        OrderProduct finalOrderProduct = orderProduct;
        List<OrderProductDetail> orderDetails = carts.stream()
                .map(cart -> OrderProductDetail.builder()
                        .orderProduct(finalOrderProduct)
                        .product(cart.getProduct())
                        .quantity(cart.getQuantity())
                        .build())
                .collect(Collectors.toList());
        orderProductDetailRepo.saveAll(orderDetails);
        cartRepo.deleteAll(carts);
        return "Order success";
    }

    public Cart getCartFromCusAndProd(Customer cus, Product prod) {
        return cartRepo.findCartByCustomerAndProduct(cus, prod)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot found cart from customer %s and product %s", cus.getId(), prod.getId())));
    }
}