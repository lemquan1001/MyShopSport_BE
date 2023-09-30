package com.example.ecommerce_be.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "Bill")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
