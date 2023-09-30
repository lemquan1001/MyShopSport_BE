package com.example.ecommerce_be.dto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BillDTO {
    private Long id;
    private Long amount;
    private String customer;
    private String phone;
    private String email;
    private String andress;
    private String status;
    private String note;
    private String payMethod;
    private Date createdDate;

}
