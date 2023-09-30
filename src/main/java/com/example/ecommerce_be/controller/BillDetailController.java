package com.example.ecommerce_be.controller;

import com.example.ecommerce_be.base.BaseResponse;
import com.example.ecommerce_be.constants.StatusCode;
import com.example.ecommerce_be.dto.BillDetailDTO;
import com.example.ecommerce_be.service.BillDetailService;
import com.example.ecommerce_be.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/billDetail")
public class BillDetailController {
    @Autowired
    private BillDetailService billDetailService;

   /* @PostMapping("/searchT")
    public  ResponseEntity searchProduct(@RequestBody ProductPayloadSearch payloadSearch, Pageable pageable){
        return ResponseEntity.ok(new BaseResponse(productService.searchProduct(payloadSearch,pageable),"Thành công", StatusCode.SUCCESS));
    }*/
   @PostMapping("/addBillDetail")
   @ResponseBody
    ResponseEntity addNewBillDetail(@RequestBody BillDetailDTO productDTO){
        return ResponseEntity.ok(new BaseResponse(billDetailService.addNewBillDetail(productDTO),"Thêm mới sản phẩm thành công",StatusCode.SUCCESS));
   }
    @GetMapping("/getAllBillDetail")
    public ResponseEntity getAllBillDetail() {
        return ResponseEntity.ok(new BaseResponse(billDetailService.getListBillDetail(),"Thành công", StatusCode.SUCCESS));
    }

}
