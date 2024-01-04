package com.crochet.spring.jpa.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "contact")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends BaseEntity {
    @Column(name = "address", length = 512, nullable = false)
    private String address;

    @Column(name = "phone", length = 32, nullable = false)
    private String phone;

    @Column(name = "ward_code")
    private int wardCode;

    @Column(name = "ward_name")
    private String wardName;

    @Column(name = "district_id")
    private int districtID;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "province_name")
    private String provinceName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", columnDefinition = "BINARY(16) NOT NULL")
    private Customer customer;
}
