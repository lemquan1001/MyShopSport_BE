package com.example.ecommerce_be.controller;


import com.example.ecommerce_be.base.BaseResponse;
import com.example.ecommerce_be.constants.StatusCode;
import com.example.ecommerce_be.dto.AccountDTO;
import com.example.ecommerce_be.dto.BrandDTO;
import com.example.ecommerce_be.service.BrandService;
import com.example.ecommerce_be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping("/getAllBrand")
    public ResponseEntity getAllBrand() {
        return ResponseEntity.ok(new BaseResponse(brandService.getListBrand(),"Thành công", StatusCode.SUCCESS));
    }
    @PostMapping("/addBrand")
    @ResponseBody
    ResponseEntity addNewbrand(@RequestBody BrandDTO brandDTO){
        return ResponseEntity.ok(new BaseResponse(brandService.addNewBrand(brandDTO),"Thêm mới brand thành công",StatusCode.SUCCESS));
    }
    @PutMapping("/updateBrand")
    @ResponseBody
    ResponseEntity updateBrand(@RequestBody BrandDTO brandDTO){
        return ResponseEntity.ok(new BaseResponse(brandService.updateBrand(brandDTO),"Cập nhật brand thành công",StatusCode.SUCCESS));
    }

    @DeleteMapping("/deleteBrand/{id}")
    ResponseEntity deleteBrand(@PathVariable(name = "id") Long id){
        brandService.deleteBrandById(id);
        return  ResponseEntity.ok(new BaseResponse(null,"Xóa Brand thành công",StatusCode.SUCCESS));
    }
}
