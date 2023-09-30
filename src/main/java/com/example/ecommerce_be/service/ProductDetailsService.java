package com.example.ecommerce_be.service;

import com.example.ecommerce_be.dto.ProductDetailsDTO;
import com.example.ecommerce_be.entity.Product_T;

import java.util.List;

public interface ProductDetailsService {


    //@Autowired
    //private ColorRepository colorRepository;
    List<ProductDetailsDTO> getListProductDetails();
    ProductDetailsDTO getBySize(Product_T product, String size);

    ProductDetailsDTO addNewProduct(ProductDetailsDTO productDTO);
    List<ProductDetailsDTO> getProductDetailById(Product_T product);
    //List<ProductDetailsDTO> getListProductDetails(ProductDetailsDTO productDetailsDTO);
    //ProductDTO updateProduct (ProductDTO productDTO);

    //ProductDTO getProductById(Long id);

    //void deleteProduct(Long id);

    //Page<ProductDTO> searchProduct(ProductPayloadSearch payloadSearch, Pageable pageable);
}
