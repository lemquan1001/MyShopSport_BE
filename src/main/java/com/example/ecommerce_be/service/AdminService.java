package com.example.ecommerce_be.service;

import com.example.ecommerce_be.dto.AccountDTO;
import com.example.ecommerce_be.dto.AdminDTO;
import com.example.ecommerce_be.dto.CredentialsDto;
import com.example.ecommerce_be.dto.SignUpDto;

public interface AdminService {
    public AdminDTO login(CredentialsDto credentialsDto);

    public AdminDTO register(SignUpDto userDto);

    public void confirmEmail(String email);
    public AdminDTO findByLogin(String login);
    public AdminDTO updateAdmin(AdminDTO adminDTO);
    public void deleteAdminById(Long id);
    public AdminDTO getAccountByAdmin(String id);
}
