package com.example.ecommerce_be.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Product_DTO implements Serializable {
    private Long id;
    private String productCode;
    private String productName;
    private String description;
//    private String brand;
    private String image;
    private Long status;
    private Long price;
    //@ManyToOne
    private CategoryDTO category;

    private BrandDTO brand;


}
