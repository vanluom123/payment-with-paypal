package com.crochet.spring.jpa.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "cart_detail")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail extends BaseEntity {
  @Column(name = "quantity", columnDefinition = "INTEGER DEFAULT 1 NOT NULL")
  @Builder.Default
  private int quantity = 1;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", columnDefinition = "BINARY(16) NOT NULL")
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id", columnDefinition = "BINARY(16) NOT NULL")
  private Cart cart;

  public double getTotalAmount() {
    return product.getPrice() * quantity;
  }
}
