package com.crochet.spring.jpa.demo.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "pattern")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Pattern extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", columnDefinition = "double default 0", nullable = false)
    private double price;

    @ElementCollection
    @CollectionTable(name = "pattern_files", joinColumns = @JoinColumn(name = "pattern_id"))
    @Column(name = "file_name")
    private List<String> files;

    @OneToMany(mappedBy = "pattern")
    private List<OrderPatternDetail> orderPatternDetails;
}
