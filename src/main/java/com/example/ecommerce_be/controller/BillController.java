package com.example.ecommerce_be.controller;

import com.example.ecommerce_be.base.BaseResponse;
import com.example.ecommerce_be.constants.StatusCode;
import com.example.ecommerce_be.dto.BillDTO;
import com.example.ecommerce_be.dto.CustomerDTO;
import com.example.ecommerce_be.dto.Product_DTO;
import com.example.ecommerce_be.entity.Bill;
import com.example.ecommerce_be.entity.Category;
import com.example.ecommerce_be.service.BillService;
import com.example.ecommerce_be.service.Product_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @RequestMapping(value = "/updateBill",method = RequestMethod.PUT,consumes =MediaType.APPLICATION_JSON_VALUE ,
            headers = MediaType.APPLICATION_JSON_VALUE)

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

    @GetMapping("/getBillStt0")
    public ResponseEntity getBillStatus0() {
        return ResponseEntity.ok(new BaseResponse(billService.getBillByStatus0(),"Thành công", StatusCode.SUCCESS));
    }

    @GetMapping("/getBillStt1")
    public ResponseEntity getBillStatus1() {
        return ResponseEntity.ok(new BaseResponse(billService.getBillByStatus1(),"Thành công", StatusCode.SUCCESS));
    }

    @GetMapping("/getBillStt2")
    public ResponseEntity getBillStatus2() {
        return ResponseEntity.ok(new BaseResponse(billService.getBillByStatus2(),"Thành công", StatusCode.SUCCESS));
    }
    @GetMapping("/getBillStt3")
    public ResponseEntity getBillStatus3() {
        return ResponseEntity.ok(new BaseResponse(billService.getBillByStatus3(),"Thành công", StatusCode.SUCCESS));
    }

    @PostMapping("/addBill")
    @ResponseBody
    ResponseEntity addNewBill(@RequestBody BillDTO billDTO){
        return ResponseEntity.ok(new BaseResponse(billService.addNewBill(billDTO),"Thêm mới sản phẩm thành công",StatusCode.SUCCESS));
    }

    @PutMapping("/updateBill")
    @ResponseBody
    ResponseEntity updateBill(@RequestBody BillDTO billDTO){
        return ResponseEntity.ok(new BaseResponse(billService.updateBill(billDTO),"Cập nhật thoong tin bill thành công",StatusCode.SUCCESS));
    }
    @GetMapping("/getBillById/{id}")
    ResponseEntity getBilltById(@PathVariable(name="id") String id){
        return ResponseEntity.ok(new BaseResponse(billService.getBillById(id),"Thành công",StatusCode.SUCCESS));
    }

    @GetMapping("/getInforById/{id}")
    ResponseEntity getInforById(@PathVariable(name="id") Long id){
        return ResponseEntity.ok(new BaseResponse(billService.getInforById(id),"Thành công",StatusCode.SUCCESS));
    }
//    public ResponseEntity<Bill> getInforById(@PathVariable("id") Long id){
//        Bill bill = billService.getInforById(id);
//        return  new ResponseEntity<>(bill, HttpStatus.OK);
//    }

}
