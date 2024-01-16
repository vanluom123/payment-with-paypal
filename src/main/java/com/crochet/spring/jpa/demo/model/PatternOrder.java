package com.crochet.spring.jpa.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pattern_order")
@SuperBuilder
@NoArgsConstructor
public class PatternOrder extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", columnDefinition = "BINARY(16) NOT NULL")
  private Customer customer;

  @OneToMany(mappedBy = "patternOrder", cascade = CascadeType.ALL)
  private Set<PatternOrderDetail> patternOrderDetails;
}
