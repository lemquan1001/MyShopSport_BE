package com.example.ecommerce_be.mapper;

import com.example.ecommerce_be.dto.AdminDTO;
import com.example.ecommerce_be.dto.SignUpDto;
import com.example.ecommerce_be.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminDTO toUserDto(Admin admin);

    @Mapping(target="password",ignore = true)
    Admin signUpToUser(SignUpDto adminDTO);
}
