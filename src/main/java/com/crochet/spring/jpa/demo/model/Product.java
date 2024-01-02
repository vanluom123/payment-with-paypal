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
@Table(name = "product")
@SuperBuilder
@NoArgsConstructor
public class Product extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Lob
    @Column(name = "description", columnDefinition = "LONGBLOB")
    private String description;

    @Column(name = "length")
    private long length;

    @Column(name = "weight")
    private long weight;

    @Column(name = "width")
    private long width;

    @Column(name = "height")
    private long height;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderProductDetail> orderProductDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> carts;

    @ElementCollection
    @CollectionTable(name = "file_modal",
            joinColumns = {@JoinColumn(name = "product_id", nullable = false)})
    @Column(name = "bytes", columnDefinition = "LONGBLOB")
    private List<String> files;
}
