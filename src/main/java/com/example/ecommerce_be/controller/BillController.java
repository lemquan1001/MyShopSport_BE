package com.example.ecommerce_be.controller;

import com.example.ecommerce_be.base.BaseResponse;
import com.example.ecommerce_be.constants.StatusCode;
import com.example.ecommerce_be.dto.BillDTO;
import com.example.ecommerce_be.dto.CustomerDTO;
import com.example.ecommerce_be.dto.Product_DTO;
import com.example.ecommerce_be.entity.Category;
import com.example.ecommerce_be.service.BillService;
import com.example.ecommerce_be.service.Product_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/bill")
public class BillController {
    @Autowired
    private BillService billService;

   /* @PostMapping("/searchT")
    public  ResponseEntity searchProduct(@RequestBody ProductPayloadSearch payloadSearch, Pageable pageable){
        return ResponseEntity.ok(new BaseResponse(productService.searchProduct(payloadSearch,pageable),"Thành công", StatusCode.SUCCESS));
    }*/

//    @PostMapping("/addProductT")
//    @ResponseBody
//    ResponseEntity addNewProduct(@RequestBody Product_DTO productDTO){
//        return ResponseEntity.ok(new BaseResponse(productService.addNewProduct(productDTO),"Thêm mới sản phẩm thành công",StatusCode.SUCCESS));
//    }
    @GetMapping("/getAllBill")
    public ResponseEntity getAllBill() {
        return ResponseEntity.ok(new BaseResponse(billService.getListBill(),"Thành công", StatusCode.SUCCESS));
    }
    @PostMapping("/addBill")
    @ResponseBody
    ResponseEntity addNewBill(@RequestBody BillDTO billDTO){
        return ResponseEntity.ok(new BaseResponse(billService.addNewBill(billDTO),"Thêm mới sản phẩm thành công",StatusCode.SUCCESS));
    }
    @GetMapping("/getBillById/{id}")
    ResponseEntity getBilltById(@PathVariable(name="id") String id){
        return ResponseEntity.ok(new BaseResponse(billService.getBillById(id),"Thành công",StatusCode.SUCCESS));
    }

}
