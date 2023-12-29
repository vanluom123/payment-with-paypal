package com.crochet.spring.jpa.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "order_product_detail")
@SuperBuilder
@NoArgsConstructor
public class OrderProductDetail extends BaseEntity {
    @Column(name = "quantity", columnDefinition = "INTEGER DEFAULT 1 NOT NULL")
    private Integer quantity = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_product_id", nullable = false)
    private OrderProduct orderProduct;
}
