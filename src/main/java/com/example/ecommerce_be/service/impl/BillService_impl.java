package com.example.ecommerce_be.service.impl;

import com.example.ecommerce_be.base.NotFoundException;
import com.example.ecommerce_be.dto.AccountDTO;
import com.example.ecommerce_be.dto.BillDTO;
import com.example.ecommerce_be.dto.CustomerDTO;
import com.example.ecommerce_be.dto.Product_DTO;
import com.example.ecommerce_be.entity.*;
import com.example.ecommerce_be.exception.UserAlreadyExistException;
import com.example.ecommerce_be.mapper.BillMapper;
import com.example.ecommerce_be.mapper.Product_Mapper;
import com.example.ecommerce_be.repositories.BillRepository;
import com.example.ecommerce_be.repositories.Product_Repository;
import com.example.ecommerce_be.service.BillService;
import com.example.ecommerce_be.service.Product_Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillService_impl implements BillService {


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
    public List<BillDTO> getBillByEmail(String id){
        return billMapper.toDtos(billRepository.getBillByEmail(id));
    }

    @Override
    public List<BillDTO> getBillByStatus0(){
        return billMapper.toDtos(billRepository.getBillByStatus0());
    }

    @Override
    public List<BillDTO> getBillByStatus1(){
        return billMapper.toDtos(billRepository.getBillByStatus1());
    }

    @Override
    public List<BillDTO> getBillByStatus2(){
        return billMapper.toDtos(billRepository.getBillByStatus2());
    }

    @Override
    public List<BillDTO> getBillByStatus3(){
        return billMapper.toDtos(billRepository.getBillByStatus3());
    }

    @Override
    public List<BillDTO> getInforById(Long id){
        return billMapper.toDtos(billRepository.getInfoProOfCusById(id));
//        return (Bill) billRepository.getInfoProOfCusById(id).orElseThrow(() -> new UserAlreadyExistException("SUCCESS"));

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
        return billMapper.toDto(billRepository.save(bill));
    }

    @Transactional
    public BillDTO updateBill(BillDTO billDTO) {
        // dùng modal mapper
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(BillDTO.class, Bill.class)
                .setProvider(p -> billRepository.findById(billDTO.getId()).orElseThrow(NoResultException::new));
        Bill bill = mapper.map(billDTO, Bill.class);

        // Lưu lại thông tin sản phẩm
        Bill updatedABill = billRepository.save(bill);

        return billMapper.toDto(billRepository.save(updatedABill));

    }



}
