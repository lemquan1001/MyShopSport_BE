package com.example.ecommerce_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDTO {
    private Long id;
    private  String username;
    private  String login;
    private  String token;
}
