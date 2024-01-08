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
    @Column(name = "name", columnDefinition = "NVARCHAR(255) NOT NULL")
    private String name;

    @Column(name = "address", columnDefinition = "NVARCHAR(512) NOT NULL")
    private String address;

    @Column(name = "phone", length = 32, nullable = false)
    private String phone;

    @Column(name = "ward_code")
    private String wardCode;

    @Column(name = "ward_name", columnDefinition = "NVARCHAR(50)")
    private String wardName;

    @Column(name = "district_id")
    private int districtID;

    @Column(name = "district_name", columnDefinition = "NVARCHAR(50)")
    private String districtName;

    @Column(name = "province_name", columnDefinition = "NVARCHAR(50)")
    private String provinceName;

    @Column(name = "is_default", columnDefinition = "BOOLEAN DEFAULT FALSE")
    public boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", columnDefinition = "BINARY(16) NOT NULL")
    private Customer customer;
}
