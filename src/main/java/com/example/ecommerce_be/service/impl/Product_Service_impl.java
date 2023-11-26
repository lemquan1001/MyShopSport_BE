package com.example.ecommerce_be.service.impl;

import com.example.ecommerce_be.base.NotFoundException;
import com.example.ecommerce_be.dto.ProductDetailsDTO;
import com.example.ecommerce_be.dto.Product_DTO;
import com.example.ecommerce_be.entity.Category;
import com.example.ecommerce_be.entity.ProductDetails;
import com.example.ecommerce_be.entity.Product_T;
import com.example.ecommerce_be.mapper.ProductDetailsMapper;
import com.example.ecommerce_be.mapper.Product_Mapper;
import com.example.ecommerce_be.repositories.ProductDetailsRepository;
import com.example.ecommerce_be.repositories.Product_Repository;
import com.example.ecommerce_be.service.Product_Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class Product_Service_impl implements Product_Service {

    @Autowired
    private Product_Repository productRepository;

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private Product_Mapper productMapper;

    //@Autowired
    //private ColorRepository colorRepository;
    @Override
    public List<Product_DTO> getListProducts() {
        return productMapper.toDtos(productRepository.getAllProduct());
    }

    @Override
    public List<Product_DTO> getListProductByCategory(Category category) {
        return productMapper.toDtos(productRepository.getAllProduct_ByCategory(category));
    }
    @Override
    public Product_DTO getProductById(Long id){
        return productMapper.toDto(productRepository.getProduct_ById(id).orElseThrow(() -> new NotFoundException("Product by id" + id + "not found")));
    }
    @Override
    public List<Product_DTO> getProductByName(String productName){
        return productMapper.toDtos(productRepository.getProduct_ByName(productName));
    }


    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Override
    @Transactional
    public Product_DTO addNewProduct(Product_DTO productDTO) {
        Product_T saveProduct = productMapper.toEntity(productDTO);
        productRepository.save(saveProduct);

        // Tự động đổ dữ liệu từ Product sang ProductDetail
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
        ProductDetails saveProductDetail = productDetailsMapper.toEntity(productDetailsDTO);
        saveProductDetail.setImage(productDTO.getImage());
        saveProductDetail.setQuantify(productDTO.getStatus());
        saveProductDetail.setProduct(saveProduct);

        // Sinh ra các size
        List<String> sizes = Arrays.asList("S", "M", "L", "XL","2XL");
        for (String size : sizes) {
            ProductDetails sizeEntity = new ProductDetails();
            sizeEntity.setImage(productDTO.getImage());
            sizeEntity.setSize(size);
            sizeEntity.setQuantify(Long.valueOf(1));
            sizeEntity.setProduct(saveProduct);
            productDetailsRepository.save(sizeEntity);

        }
        if (saveProductDetail.getSize() != null) {
            productDetailsRepository.save(saveProductDetail);
        }
        // Trả về entityA đã được lưu
        return productMapper.toDto(productRepository.save(saveProduct));

    }

    @Transactional
    public Product_DTO updateProduct(Product_DTO productDTO) {


        //dùng modal mapper
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Product_DTO.class,Product_T.class)
                .setProvider(p -> productRepository.findById(productDTO.getId()).orElseThrow(NoResultException::new));
        Product_T product = mapper.map(productDTO, Product_T.class);

        // Lưu lại thông tin sản phẩm
        Product_T updatedProduct = productRepository.save(product);

        // Cập nhật thông tin trong ProductDetails
        updateProductDetailsImage(updatedProduct.getId(), productDTO.getImage());
        return productMapper.toDto(productRepository.save(product));

    }

    private void updateProductDetailsImage(Long productId, String newImage) {
        // Lấy danh sách ProductDetails dựa trên productId
        List<ProductDetails> productDetailsList = productDetailsRepository.findByProduct_Id(productId);

        // Cập nhật trường image trong ProductDetails
        for (ProductDetails productDetails : productDetailsList) {
            productDetails.setImage(newImage);
            productDetailsRepository.save(productDetails);
        }

    }

    @Transactional
    public void deleteProductById(Long productId) {
        // Tìm đối tượng thực thể trong cơ sở dữ liệu
        Product_T productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new NoResultException("Không tìm thấy sản phẩm với ID: " + productId));

        // Xóa các hàng liên kết trong EntityB trước
        productDetailsRepository.deleteByProduct_Id(productId);
        // Xóa đối tượng thực thể khỏi cơ sở dữ liệu
        productRepository.delete(productEntity);
    }

//    @Override
//    public void deleteProduct(Long id) {
//
//        productRepository.deleteProductById(id);
//    }

    /*@Override
    @Transactional
    public Product_DTO updateProduct(Product_DTO productDTO) {
        Optional<Product_T> product = checkProduct(productDTO.getId());
        if (product.isPresent()) {
            return  productMapper.toDto(productRepository.save(productMapper.toEntity(productDTO)));

        } else {
            throw new NotFoundException("Product not found");
        }
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productMapper.toDto(productRepository.getProductById(id).orElseThrow(() -> new NotFoundException("Product by id" + id + "not found")));
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Optional<Product> product = checkProduct(id);
        if (product.isPresent()) {
            productRepository.deleteProductById(id);
        } else {
            throw new NotFoundException("Product not found");
        }
    }

    @Override
    public Page<ProductDTO> searchProduct(ProductPayloadSearch payloadSearch, Pageable pageable) {
        if(payloadSearch.getColors() != null && payloadSearch.getColors().isEmpty()){
            payloadSearch.setColors(null);
        }
        return productRepository.searchProduct(payloadSearch.getFromPrice(),payloadSearch.getToPrice(),payloadSearch.getColors(),pageable).map(item -> productMapper.toDto(item));
    }

    public Optional<Product> checkProduct(Long id) {
        return productRepository.getProductById(id);
    }*/

}
