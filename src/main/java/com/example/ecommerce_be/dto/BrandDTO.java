package com.example.ecommerce_be.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class BrandDTO {
    private Long id;

    private String brandName;

    private String brandCode;

    private String description;

}
