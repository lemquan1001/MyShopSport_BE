package com.example.ecommerce_be.service;

import com.example.ecommerce_be.dto.Product_DTO;
import com.example.ecommerce_be.entity.Category;

import java.util.List;

public interface Product_Service {
    List<Product_DTO> getListProducts();
    List<Product_DTO> getListProductsEnable();
    List<Product_DTO> getListProductByCategory(Category category);
    Product_DTO getProductById(Long id);
    List<Product_DTO> getProductByName(String productName);
    Product_DTO addNewProduct(Product_DTO productDTO);

    Product_DTO updateProduct (Product_DTO productDTO);

    //Product_DTO getProductById(Long id);

    void deleteProductById(Long id);

    //Page<ProductDTO> searchProduct(ProductPayloadSearch payloadSearch, Pageable pageable);
}
