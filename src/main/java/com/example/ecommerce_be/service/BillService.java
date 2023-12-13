package com.example.ecommerce_be.service;

import com.example.ecommerce_be.dto.BillDTO;
import com.example.ecommerce_be.dto.Product_DTO;
import com.example.ecommerce_be.entity.Bill;
import com.example.ecommerce_be.entity.Category;

import java.util.List;

public interface BillService {


    List<BillDTO> getListBill();
    List<BillDTO> getBillByStatus0();
    List<BillDTO> getBillByStatus1();
    List<BillDTO> getBillByStatus2();
    List<BillDTO> getBillByStatus3();
    BillDTO addNewBill(BillDTO billDTO);
    List<BillDTO> getBillByEmail(String id);
    public List<BillDTO> getInforById(Long id);
    public BillDTO updateBill(BillDTO billDTO);


}
