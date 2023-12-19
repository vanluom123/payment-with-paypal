package com.crochet.spring.jpa.demo.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @OneToMany(mappedBy = "product")
    private List<OrderProductDetail> orderProductDetails;

    @ElementCollection
    @CollectionTable(name = "file_modal",
            joinColumns = {@JoinColumn(name = "product_id", nullable = false)})
    @Column(name = "bytes", columnDefinition = "LONGBLOB")
    private List<String> files;
}
