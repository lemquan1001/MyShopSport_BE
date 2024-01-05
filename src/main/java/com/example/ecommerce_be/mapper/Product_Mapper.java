package com.example.ecommerce_be.mapper;

import com.example.ecommerce_be.dto.Product_DTO;
import com.example.ecommerce_be.entity.Product_T;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {Product_Mapper.class})
public interface Product_Mapper {
    @Mapping(target = "productCode",source = "productCode")
    @Mapping(target = "productName",source = "productName")
    @Mapping(target = "category",source = "category")
    //@Mapping(target = "colors",ignore = true)
    Product_T toEntity(Product_DTO productDTO);

    @Mapping(target = "productCode",source = "productCode")
    @Mapping(target = "productName",source = "productName")
    @Mapping(target = "category",source = "category")
    //@Mapping(target = "colors",qualifiedByName = "toColorDTO")
    Product_DTO toDto(Product_T product);

    List<Product_DTO> toDtos(List<Product_T> datas);

   // @Named(value = "toColorDTO")
    //default List<String> toColorDTO(List<Color> colorList){
       // return colorList.stream().map(item-> item.getCode()).collect(Collectors.toList());
    //}

}
