package com.example.ecommerce_be.controller;


import com.example.ecommerce_be.base.BaseResponse;
import com.example.ecommerce_be.constants.StatusCode;
import com.example.ecommerce_be.service.BrandService;
import com.example.ecommerce_be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping("/getAllBrand")
    public ResponseEntity getAllBrand() {
        return ResponseEntity.ok(new BaseResponse(brandService.getListBrand(),"Thành công", StatusCode.SUCCESS));
    }
}
