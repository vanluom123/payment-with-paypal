package com.crochet.spring.jpa.demo.model;

import com.crochet.spring.jpa.demo.type.paypal.CurrencyCode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
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
@Table(name = "pattern")
@SuperBuilder
@NoArgsConstructor
public class Pattern extends BaseEntity {
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "price", columnDefinition = "DOUBLE CHECK (price > 0) NOT NULL")
  private double price;

  @Enumerated(EnumType.STRING)
  @Column(name = "currency_code", columnDefinition = "VARCHAR(20) DEFAULT 'USD'", nullable = false)
  private CurrencyCode currencyCode;

  @ElementCollection
  @CollectionTable(name = "pattern_files",
      joinColumns = @JoinColumn(name = "pattern_id", columnDefinition = "BINARY(16) NOT NULL"))
  @Column(name = "file_name", columnDefinition = "LONGBLOB")
  private Set<String> files;

  @OneToMany(mappedBy = "pattern", cascade = CascadeType.ALL)
  private Set<PatternOrderDetail> patternOrderDetails;
}
