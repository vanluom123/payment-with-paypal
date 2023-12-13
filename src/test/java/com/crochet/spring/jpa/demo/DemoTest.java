//package com.crochet.spring.jpa.demo;
//
//import com.crochet.spring.jpa.demo.model.Customer;
//import com.crochet.spring.jpa.demo.model.Order;
//import com.crochet.spring.jpa.demo.model.Product;
//import com.crochet.spring.jpa.demo.repository.CustomerRepository;
//import com.crochet.spring.jpa.demo.repository.OrderRepository;
//import com.crochet.spring.jpa.demo.repository.ProductRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class DemoTest {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Test
//    void createCustomer() {
//        Customer customer = new Customer();
//        customer.setName("Test");
//        customer.setAddress("Test");
//        customer.setEmail("Test@gmail.com");
//        customer.setPhone("123456789");
//        customer.setUsername("Test");
//        customer.setPassword("Test");
//        customer = customerRepository.save(customer);
//        assertEquals("USER", customer.getRole().toString());
//    }
//
//    @Test
//    void createProduct() {
//        Product product = new Product();
//        product.setName("Test01");
//        product.setPrice(1000);
//        product.setDescription("Test01");
//        product = productRepository.save(product);
//        assertEquals("Test01", product.getName());
//    }
//
//    @Test
//    void createOrder() {
//        var customer = customerRepository
//                .findById(UUID.fromString("0d168230-e0f9-4347-a913-f6321cee8222"))
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//        var product = productRepository.findById(UUID.fromString("807bcb2f-7a88-4842-800a-f8cb3f1c6ed7"))
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//        Order order = new Order();
//        order.setCustomer(customer);
//        order.setProduct(product);
//        order = orderRepository.save(order);
//        assertEquals(1, order.getQuantity());
//        assertEquals("0d168230-e0f9-4347-a913-f6321cee8222", order.getCustomer().getId().toString());
//        assertEquals("807bcb2f-7a88-4842-800a-f8cb3f1c6ed7", order.getProduct().getId().toString());
//    }
//
//    @Test
//    void getCustomers() {
//        var customers = customerRepository.findAll();
//        assertNotNull(customers);
//    }
//
//    @Test
//    void getOrderByCustomerId() {
//        var order = orderRepository.findOrderByCustomerId(UUID.fromString("0d168230-e0f9-4347-a913-f6321cee8222"))
//                .orElse(null);
//        assertNotNull(order);
//    }
//}
