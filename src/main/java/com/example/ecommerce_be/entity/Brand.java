package com.example.ecommerce_be.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "brands")
@Entity
@NoArgsConstructor
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String brandName;
    @Column(nullable = false, unique = true)
    private String brandCode;
    @Column(nullable = false, unique = true)
    private String description;


    @OneToMany(mappedBy = "brand")
    private List<Product_T> products = new ArrayList<>();
}
