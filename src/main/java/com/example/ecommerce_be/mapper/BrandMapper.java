package com.example.ecommerce_be.mapper;

import com.example.ecommerce_be.dto.BrandDTO;
import com.example.ecommerce_be.entity.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {BrandMapper.class})
public interface BrandMapper {
    Brand toEntity(BrandDTO brandDTO);
    BrandDTO toDto(Brand brand);

    List<BrandDTO> toListDto(List<Brand> brands);
}
