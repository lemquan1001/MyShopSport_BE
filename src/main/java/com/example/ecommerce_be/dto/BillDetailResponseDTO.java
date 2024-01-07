package com.example.ecommerce_be.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillDetailResponseDTO {
    private String brand;
    private String description;
    private String productName;
    private String size;
    private int quantify;
    private double price;
}
