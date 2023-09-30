package com.example.ecommerce_be.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Table(name = "BillDetail")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productDetailId;
    private Long quantify;

    private Long billID;


}
