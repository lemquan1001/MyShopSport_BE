package com.example.ecommerce_be.mapper;

import com.example.ecommerce_be.dto.BillDTO;
import com.example.ecommerce_be.dto.Product_DTO;
import com.example.ecommerce_be.entity.Bill;
import com.example.ecommerce_be.entity.Product_T;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CategoryMapper.class})
public interface BillMapper {
    //@Mapping(target = "customer",source = "customer")
    //@Mapping(target = "colors",ignore = true)
    Bill toEntity(BillDTO billDTO);

    //@Mapping(target = "customer",source = "customer")
    //@Mapping(target = "colors",qualifiedByName = "toColorDTO")
    BillDTO toDto(Bill bill);

    List<BillDTO> toDtos(List<Bill> datas);

   // @Named(value = "toColorDTO")
    //default List<String> toColorDTO(List<Color> colorList){
       // return colorList.stream().map(item-> item.getCode()).collect(Collectors.toList());
    //}

}
