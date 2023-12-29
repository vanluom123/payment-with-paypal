package com.crochet.spring.jpa.demo.model;

import com.crochet.spring.jpa.demo.type.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "order_product")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "VARCHAR(25) DEFAULT 'PENDING' NOT NULL")
    private OrderStatus status;

    @Column(name = "total_amount", columnDefinition = "DOUBLE DEFAULT 0 NOT NULL")
    private Double totalAmount;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orderProduct", cascade = CascadeType.ALL)
    private List<OrderProductDetail> orderProductDetails;

    @OneToMany(mappedBy = "orderProduct", cascade = CascadeType.ALL)
    private List<Payment> payments;
}
