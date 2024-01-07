package com.example.ecommerce_be.controller;

import com.example.ecommerce_be.base.BaseResponse;
import com.example.ecommerce_be.constants.StatusCode;
import com.example.ecommerce_be.dto.BillDetailDTO;
import com.example.ecommerce_be.dto.BillDetailResponseDTO;
import com.example.ecommerce_be.entity.Brand;
import com.example.ecommerce_be.service.BillDetailService;
import com.example.ecommerce_be.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private EntityManager entityManager;
    @GetMapping("/getByBillId/{billId}")
    public ResponseEntity<List<BillDetailResponseDTO>> getByBillId(@PathVariable Long billId) {
        String queryString = "SELECT c.brand, c.description, c.productName, b.size, a.quantify, c.price " +
                "FROM BillDetail a, ProductDetails b, Product_T c " +
                "WHERE a.productDetailId = b.id AND b.product = c.id AND a.billID = :billId";

//        String queryString = "SELECT c.brand, c.description, c.productName, b.size, a.quantify, c.price " +
//                "FROM BillDetail a, ProductDetails b, com.example.ecommerce_be.entity.Product_T c " +
//                "WHERE a.productDetailId = b.id AND b.product = c.id AND a.billID = :billId";


        List<Object[]> queryResult = entityManager.createQuery(queryString, Object[].class)
                .setParameter("billId", billId)
                .getResultList();

        List<BillDetailResponseDTO> response = new ArrayList<>();

        for (Object[] result : queryResult) {
            BillDetailResponseDTO dto = new BillDetailResponseDTO();
//            dto.setBrand((String) result[0]);
            dto.setBrand(((Brand) result[0]).getBrandName());
            dto.setDescription((String) result[1]);
            dto.setProductName((String) result[2]);
            dto.setSize((String) result[3]);
//            dto.setQuantify((int) result[4]);
            dto.setQuantify(((Long) result[4]).intValue());
            dto.setPrice(((Long) result[5]).doubleValue());
//            dto.setPrice((double) result[5]);
            response.add(dto);
        }

        return ResponseEntity.ok(response);
    }
}
