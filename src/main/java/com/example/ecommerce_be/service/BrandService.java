package com.example.ecommerce_be.service;

import com.example.ecommerce_be.dto.BrandDTO;

import java.util.List;

public interface BrandService {
    public List<BrandDTO> getListBrand();
    public BrandDTO addNewBrand(BrandDTO brandDTO);
    public BrandDTO updateBrand(BrandDTO brandDTO);
    public void deleteBrandById(Long id);
}
