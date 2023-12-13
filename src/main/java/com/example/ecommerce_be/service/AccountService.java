package com.example.ecommerce_be.service;

import com.example.ecommerce_be.dto.AccountDTO;
import com.example.ecommerce_be.dto.CustomerDTO;
import com.example.ecommerce_be.dto.Product_DTO;

import java.util.List;

public interface AccountService {


    List<AccountDTO> getListAccount();
    AccountDTO addNewAccount(AccountDTO accountDTO);
    public AccountDTO updateAccount(AccountDTO accountDTO);

    public void deleteAccountById(Long id);
    AccountDTO getAccountByUser(String id);
    boolean checkLogin(String username, String password);
    //Product_DTO updateProduct (Product_DTO productDTO);

    //Product_DTO getProductById(Long id);

    //void deleteProduct(Long id);

    //Page<ProductDTO> searchProduct(ProductPayloadSearch payloadSearch, Pageable pageable);
}
