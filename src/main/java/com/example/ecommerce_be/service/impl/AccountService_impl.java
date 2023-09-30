package com.example.ecommerce_be.service.impl;

import com.example.ecommerce_be.base.NotFoundException;
import com.example.ecommerce_be.constants.Constants;
import com.example.ecommerce_be.dto.AccountDTO;
import com.example.ecommerce_be.dto.Product_DTO;
import com.example.ecommerce_be.entity.Account;
import com.example.ecommerce_be.exception.UserAlreadyExistException;
import com.example.ecommerce_be.mapper.AccoutMapper;
import com.example.ecommerce_be.repositories.AccountRepository;
import com.example.ecommerce_be.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class AccountService_impl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccoutMapper accoutMapper;

    //@Autowired
    //private ColorRepository colorRepository;
    @Override
    public List<AccountDTO> getListAccount() {
//        return accoutMapper.toDtos(accountRepository.getAllAccount());
        return null;
    }
    @Override
    public AccountDTO getAccountByUser(String id){
        return accoutMapper.toDto(accountRepository.getAccountByUser(id).orElseThrow(() -> new NotFoundException("Product by id" + id + "not found")));
    }


    @Override
    @Transactional
    public AccountDTO addNewAccount(AccountDTO accountDTO) {
        String username = accountDTO.getUser();
        if (accountRepository.findByUsername(username) != null) {
            throw new UserAlreadyExistException("Username already exists");
        }
        Account account = accoutMapper.toEntity(accountDTO);
        account.setUser(accountDTO.getUser());
        account.setPass(accountDTO.getPass());
        account.setName(accountDTO.getName());
        account.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setEmail(accountDTO.getEmail());
        account.setAddress(accountDTO.getAddress());
        return accoutMapper.toDto(accountRepository.save(account));

    }

    public boolean checkLogin(String user, String pass) {
        Account account = accountRepository.findByUsername(user);
        if (account != null && account.getPass().equals(pass)) {
            return true; // đăng nhập thành công
        } else {
            return false; // tài khoản hoặc mật khẩu không đúng
        }
    }
}
