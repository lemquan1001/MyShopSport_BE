package com.example.ecommerce_be.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor

public class BillDetailDTO {
    private Long id;
    private Long productDetailId;
    private Long quantify;
    private Long billID;
}
