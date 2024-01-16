package com.crochet.spring.jpa.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "product")
@SuperBuilder
@NoArgsConstructor
public class Product extends BaseEntity {
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "price", columnDefinition = "DOUBLE CHECK (price > 0) NOT NULL")
  private double price;

  @Lob
  @Column(name = "description", columnDefinition = "LONGBLOB")
  private String description;

  @Column(name = "length")
  private int length;

  @Column(name = "weight")
  private int weight;

  @Column(name = "width")
  private int width;

  @Column(name = "height")
  private int height;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private Set<ProductOrderDetail> productOrderDetails;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private Set<CartDetail> cartDetails;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", columnDefinition = "BINARY(16) NOT NULL")
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "shop_id", columnDefinition = "BINARY(16) NOT NULL")
  private Shop shop;

  @ElementCollection
  @CollectionTable(name = "file_modal",
      joinColumns = {@JoinColumn(name = "product_id", columnDefinition = "BINARY(16) NOT NULL")})
  @Column(name = "bytes", columnDefinition = "LONGBLOB")
  private Set<String> files;
}
