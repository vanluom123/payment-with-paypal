package com.crochet.spring.jpa.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "order_pattern")
@SuperBuilder
@NoArgsConstructor
public class OrderPattern extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orderPattern", cascade = CascadeType.ALL)
    private List<OrderPatternDetail> orderPatternDetails;
}
