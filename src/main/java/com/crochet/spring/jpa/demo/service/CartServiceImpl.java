package com.crochet.spring.jpa.demo.service;

import com.crochet.spring.jpa.demo.model.*;
import com.crochet.spring.jpa.demo.repository.CartRepo;
import com.crochet.spring.jpa.demo.repository.CustomerRepo;
import com.crochet.spring.jpa.demo.repository.OrderProductDetailRepo;
import com.crochet.spring.jpa.demo.repository.OrderProductRepo;
import com.crochet.spring.jpa.demo.service.contact.CartService;
import com.crochet.spring.jpa.demo.type.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Override
    public String placeOrder(String customerId) {
        // Kiem tra customer co ton tai khong
        UUID cusUUID = UUID.fromString(customerId);
        var customer = customerRepo.findById(cusUUID)
                .orElseThrow(() -> new RuntimeException("Customer not existed"));
        // Lay product tu cart
        var products = cartRepo.findProductFromCartByCustomerId(customer);
        if (ObjectUtils.isEmpty(products)) {
            throw new RuntimeException("Cannot any product existed in cart");
        }
        // Lay amount nhan cho quantity roi cong lai
        double totalAmount = products.stream()
                .map(p -> {
                    // Lay cart cua customer ra de lay quantity cua product trong cart
                    var cart = getCartFromCusAndProd(customer, p);
                    return p.getPrice() * cart.getQuantity();
                })
                .reduce(0.0, Double::sum);
        // tao order
        OrderProduct orderProduct = OrderProduct.builder()
                .customer(customer)
                .status(OrderStatus.PENDING)
                .orderDate(Date.from(Instant.now()))
                .totalAmount(totalAmount)
                .build();
        orderProduct = orderProductRepo.save(orderProduct);
        // do du lieu tu cart vao order detail
        List<Cart> carts = cartRepo.findCartByCus(customer);
        for (var cart : carts) {
            var orderProdDetail = OrderProductDetail.builder()
                    .orderProduct(orderProduct)
                    .product(cart.getProduct())
                    .quantity(cart.getQuantity())
                    .build();
            orderProductDetailRepo.save(orderProdDetail);
            cartRepo.deleteCartByCustomerId(customer, cart.getProduct());
        }
        return "Order success";
    }

    public Cart getCartFromCusAndProd(Customer cus, Product prod) {
        return cartRepo.findCartByCusAndProd(cus, prod)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot found cart from customer %s and product %s", cus.getId(), prod.getId())));
    }
}