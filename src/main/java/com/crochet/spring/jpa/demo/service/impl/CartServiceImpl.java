package com.crochet.spring.jpa.demo.service.impl;

import com.crochet.spring.jpa.demo.model.Cart;
import com.crochet.spring.jpa.demo.model.Customer;
import com.crochet.spring.jpa.demo.model.OrderProduct;
import com.crochet.spring.jpa.demo.model.OrderProductDetail;
import com.crochet.spring.jpa.demo.model.Product;
import com.crochet.spring.jpa.demo.payload.dto.ghn.order.GHNCreateOrderRequest;
import com.crochet.spring.jpa.demo.repository.CartRepo;
import com.crochet.spring.jpa.demo.repository.CustomerRepo;
import com.crochet.spring.jpa.demo.repository.OrderProductDetailRepo;
import com.crochet.spring.jpa.demo.repository.OrderProductRepo;
import com.crochet.spring.jpa.demo.service.CartService;
import com.crochet.spring.jpa.demo.service.ghn.GHNService;
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
    @Autowired
    private GHNService ghnService;

    @Override
    public String addProductToCart(UUID customerId,
                                   UUID productId,
                                   int quantity) {
        cartRepo.addProductToCart(customerId, productId, quantity);
        return "Added to cart";
    }

    @Override
    public String placeOrder_2(UUID cusId) {
        cartRepo.placeOrder(cusId);
        return "Order success";
    }

    @Transactional
    @Override
    public String placeOrder(UUID customerId) {
        // Kiem tra customer co ton tai khong
        var customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not existed"));

        // Lay danh sach cart theo customer
        List<Cart> carts = cartRepo.findAllCartByCustomer(customer);
        if (ObjectUtils.isEmpty(carts)) {
            throw new RuntimeException("Cart not existed");
        }

        GHNCreateOrderRequest ghnCreateOrderRequest = GHNCreateOrderRequest.builder()
                .toName("TinTest124")
                .toPhone("0987654321")
                .toAddress("72 Thành Thái, Phường 14, Quận 10, Hồ Chí Minh, Vietnam")
                .toWardCode("20308")
                .toDistrictId(1444)
                .content("The New York Times")
                .weight(200)
                .length(1)
                .width(19)
                .height(10)
                .build();

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