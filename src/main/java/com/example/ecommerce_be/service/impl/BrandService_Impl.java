package com.example.ecommerce_be.service.impl;

import com.example.ecommerce_be.dto.AccountDTO;
import com.example.ecommerce_be.dto.BrandDTO;
import com.example.ecommerce_be.dto.CategoryDTO;
import com.example.ecommerce_be.entity.*;
import com.example.ecommerce_be.exception.UserAlreadyExistException;
import com.example.ecommerce_be.jwt.exceptions.AppException;
import com.example.ecommerce_be.mapper.BrandMapper;
import com.example.ecommerce_be.mapper.CategoryMapper;
import com.example.ecommerce_be.repositories.BrandRepository;
import com.example.ecommerce_be.repositories.CategoryRepository;
import com.example.ecommerce_be.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
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


    private boolean brandExists(String brand) {
        return brandRepository.existsByBrand(brand);
    }

    @Override
    public BrandDTO addNewBrand(BrandDTO brandDTO){
        if (brandExists(brandDTO.getBrandName())) {
        throw new AppException("Brand already exists: " + brandDTO.getBrandName(), HttpStatus.BAD_REQUEST);
    }

//        Brand brand = new ModelMapper().map(brandDTO,Brand.class);
//        brandRepository.save(brand);
//
//        return brandDTO;

        Brand brand = brandMapper.toEntity(brandDTO);
        brand.setBrandName(brandDTO.getBrandName());
        brand.setBrandCode(brandDTO.getBrandCode());
        brand.setDescription(brandDTO.getDescription());


        return brandMapper.toDto(brandRepository.save(brand));


    }



    @Transactional
    public BrandDTO updateBrand(BrandDTO brandDTO) {
        // dùng modal mapper
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(BrandDTO.class, Brand.class)
                .setProvider(p -> brandRepository.findById(brandDTO.getId()).orElseThrow(NoResultException::new));
        Brand brand = mapper.map(brandDTO, Brand.class);

        // Lưu lại thông tin sản phẩm
        Brand updatedBrand= brandRepository.save(brand);

        return brandMapper.toDto(brandRepository.save(updatedBrand));

    }


    @Transactional
    public void deleteBrandById(Long id) {
        // Tìm đối tượng thực thể trong cơ sở dữ liệu
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new NoResultException("Không tìm thấy brand với ID: " + id));


        // Xóa tài khoản
        brandRepository.delete(brand);
    }

}
