package com.example.ecommerce_be.service.impl;

import com.example.ecommerce_be.dto.BrandDTO;
import com.example.ecommerce_be.dto.CategoryDTO;
import com.example.ecommerce_be.mapper.BrandMapper;
import com.example.ecommerce_be.mapper.CategoryMapper;
import com.example.ecommerce_be.repositories.BrandRepository;
import com.example.ecommerce_be.repositories.CategoryRepository;
import com.example.ecommerce_be.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService_Impl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<BrandDTO> getListBrand() {
        return brandMapper.toListDto(brandRepository.getAllBrand());
    }

}
