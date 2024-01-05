package com.example.ecommerce_be.mapper;

import com.example.ecommerce_be.dto.AdminDTO;
import com.example.ecommerce_be.dto.BrandDTO;
import com.example.ecommerce_be.dto.SignUpDto;
import com.example.ecommerce_be.entity.Admin;
import com.example.ecommerce_be.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminDTO toUserDto(Admin admin);

    @Mapping(target="password",ignore = true)
    Admin signUpToUser(SignUpDto adminDTO);
    List<AdminDTO> toListDto(List<Admin> admins);
}
