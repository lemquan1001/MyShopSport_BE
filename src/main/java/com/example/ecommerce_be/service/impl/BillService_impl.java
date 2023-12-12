package com.example.ecommerce_be.service.impl;

import com.example.ecommerce_be.base.NotFoundException;
import com.example.ecommerce_be.dto.BillDTO;
import com.example.ecommerce_be.dto.CustomerDTO;
import com.example.ecommerce_be.dto.Product_DTO;
import com.example.ecommerce_be.entity.Bill;
import com.example.ecommerce_be.entity.Category;
import com.example.ecommerce_be.entity.Customer;
import com.example.ecommerce_be.entity.Product_T;
import com.example.ecommerce_be.mapper.BillMapper;
import com.example.ecommerce_be.mapper.Product_Mapper;
import com.example.ecommerce_be.repositories.BillRepository;
import com.example.ecommerce_be.repositories.Product_Repository;
import com.example.ecommerce_be.service.BillService;
import com.example.ecommerce_be.service.Product_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class BillService_impl implements BillService {

    @Autowired
    private BillRepository productRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillMapper billMapper;

    //@Autowired
    //private ColorRepository colorRepository;
    @Override
    public List<BillDTO> getListBill() {
        return billMapper.toDtos(billRepository.getAllBill());
    }
    @Override
    public List<BillDTO> getBillById(String id){
        return billMapper.toDtos(productRepository.getBillById(id));
    }

    @Override
    public List<BillDTO> getBillByStatus0(){
        return billMapper.toDtos(productRepository.getBillByStatus0());
    }

    @Override
    public List<BillDTO> getBillByStatus1(){
        return billMapper.toDtos(productRepository.getBillByStatus1());
    }

    @Override
    public List<BillDTO> getBillByStatus2(){
        return billMapper.toDtos(productRepository.getBillByStatus2());
    }

    @Override
    public List<BillDTO> getBillByStatus3(){
        return billMapper.toDtos(productRepository.getBillByStatus3());
    }

    @Override
    @Transactional
    public BillDTO addNewBill(BillDTO billDTO) {
        Bill bill = billMapper.toEntity(billDTO);
        bill.setAmount(billDTO.getAmount());
        bill.setCustomer(billDTO.getCustomer());
        bill.setPhone(billDTO.getPhone());
        bill.setEmail(billDTO.getEmail());
        bill.setAndress(billDTO.getAndress());
        bill.setStatus("0");
        bill.setNote(billDTO.getNote());
        bill.setPayMethod(billDTO.getPayMethod());
        bill.setCreatedDate(new Date());
        return billMapper.toDto(productRepository.save(bill));
    }



}
