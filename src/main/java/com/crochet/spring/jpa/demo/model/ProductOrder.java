package com.crochet.spring.jpa.demo.model;

import com.crochet.spring.jpa.demo.type.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product_order")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder extends BaseEntity {
  @Enumerated(EnumType.STRING)
  @Column(name = "status", columnDefinition = "VARCHAR(25) DEFAULT 'PENDING' NOT NULL")
  private OrderStatus status;

  @Column(name = "total_amount", columnDefinition = "DOUBLE CHECK (total_amount > 0) NOT NULL")
  private double totalAmount;

  @CreationTimestamp
  @Temporal(TemporalType.DATE)
  @Column(name = "order_date", nullable = false)
  private Date orderDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", columnDefinition = "BINARY(16) NOT NULL")
  private Customer customer;

  @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL)
  private Set<ProductOrderDetail> productOrderDetails;

  @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL)
  private Set<Payment> payments;
}
