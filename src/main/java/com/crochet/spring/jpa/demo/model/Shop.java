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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shop")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Shop extends BaseEntity {
    @Column(name = "shop_name", columnDefinition = "NVARCHAR(255)")
    private String shopName;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    private String address;

    @Column(name = "ward_code")
    private String wardCode;

    @Column(name = "ward_name", columnDefinition = "NVARCHAR(255)")
    private String wardName;

    @Column(name = "district_id")
    private int districtID;

    @Column(name = "district_name", columnDefinition = "NVARCHAR(255)")
    private String districtName;

    @Column(name = "province_name", columnDefinition = "NVARCHAR(255)")
    private String provinceName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Product> products;
}
