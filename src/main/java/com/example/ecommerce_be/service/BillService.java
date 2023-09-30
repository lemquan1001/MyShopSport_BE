package com.example.ecommerce_be.service;

import com.example.ecommerce_be.dto.BillDTO;
import com.example.ecommerce_be.dto.Product_DTO;
import com.example.ecommerce_be.entity.Category;

import java.util.List;

public interface BillService {


    List<BillDTO> getListBill();
    BillDTO addNewBill(BillDTO billDTO);
    List<BillDTO> getBillById(String id);


}
