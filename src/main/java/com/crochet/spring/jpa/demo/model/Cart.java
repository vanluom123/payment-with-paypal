package com.crochet.spring.jpa.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cart")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {
  private static final int ZERO = 0;

  @Column(name = "total_amount", columnDefinition = "DOUBLE CHECK (total_amount > 0) NOT NULL")
  @Builder.Default
  private double totalAmount = ZERO;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
  private Set<CartDetail> cartDetails;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", columnDefinition = "BINARY(16) NOT NULL")
  private Customer customer;

  public double getTotalAmount() {
    if (totalAmount == ZERO) {
      totalAmount = cartDetails.stream()
          .mapToDouble(CartDetail::getTotalAmount)
          .sum();
    }
    return totalAmount;
  }
}
