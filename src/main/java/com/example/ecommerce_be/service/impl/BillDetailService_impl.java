package com.example.ecommerce_be.service.impl;

import com.example.ecommerce_be.dto.BillDTO;
import com.example.ecommerce_be.dto.BillDetailDTO;
import com.example.ecommerce_be.entity.Bill;
import com.example.ecommerce_be.entity.BillDetail;
import com.example.ecommerce_be.mapper.BillDetailMapper;
import com.example.ecommerce_be.mapper.BillMapper;
import com.example.ecommerce_be.repositories.BillDetailRepository;
import com.example.ecommerce_be.repositories.BillRepository;
import com.example.ecommerce_be.service.BillDetailService;
import com.example.ecommerce_be.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class BillDetailService_impl implements BillDetailService {

    @Autowired
    private BillDetailRepository productRepository;

    @Autowired
    private BillDetailMapper productMapper;

    //@Autowired
    //private ColorRepository colorRepository;
    @Override
    public List<BillDetailDTO> getListBillDetail() {
        return productMapper.toDtos(productRepository.getAllBillDetail());
    }
    @Override
    @Transactional
    public BillDetailDTO addNewBillDetail(BillDetailDTO billDetailDTO) {
        BillDetail bill = productMapper.toEntity(billDetailDTO);
        bill.setProductDetailId(billDetailDTO.getProductDetailId());
        bill.setQuantify(billDetailDTO.getQuantify());
        bill.setBillID(billDetailDTO.getBillID());


        return productMapper.toDto(productRepository.save(bill));
    }


}
