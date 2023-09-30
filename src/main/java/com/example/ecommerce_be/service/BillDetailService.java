package com.example.ecommerce_be.service;

import com.example.ecommerce_be.dto.BillDTO;
import com.example.ecommerce_be.dto.BillDetailDTO;

import java.util.List;

public interface BillDetailService {


    List<BillDetailDTO> getListBillDetail();
    BillDetailDTO addNewBillDetail(BillDetailDTO billDetailDTO);


}
