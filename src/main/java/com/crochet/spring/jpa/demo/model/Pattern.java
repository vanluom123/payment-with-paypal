package com.crochet.spring.jpa.demo.model;

import com.crochet.spring.jpa.demo.type.paypal.CurrencyCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pattern")
@SuperBuilder
@NoArgsConstructor
public class Pattern extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", columnDefinition = "double default 0", nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_code", columnDefinition = "varchar(20) default 'USD'", nullable = false)
    private CurrencyCode currencyCode;

    @ElementCollection
    @CollectionTable(name = "pattern_files",
            joinColumns = @JoinColumn(name = "pattern_id", nullable = false))
    @Column(name = "file_name", columnDefinition = "LONGBLOB")
    private List<String> files;

    @OneToMany(mappedBy = "pattern")
    private List<OrderPatternDetail> orderPatternDetails;
}
