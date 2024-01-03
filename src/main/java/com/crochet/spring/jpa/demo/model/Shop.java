package com.crochet.spring.jpa.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "shop")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Shop extends BaseEntity {
    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "ward_code")
    private String wardCode;

    @Column(name = "ward_name")
    private String wardName;

    @Column(name = "district_id")
    private int districtID;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "province_name")
    private String provinceName;

    @OneToOne(mappedBy = "shop")
    private Customer customer;
}
