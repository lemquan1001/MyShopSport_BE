package com.example.ecommerce_be.mapper;

import com.example.ecommerce_be.dto.BillDTO;
import com.example.ecommerce_be.dto.BillDetailDTO;
import com.example.ecommerce_be.entity.Bill;
import com.example.ecommerce_be.entity.BillDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {BillDetailMapper.class})
public interface BillDetailMapper {
    @Mapping(target = "productDetailId",source = "productDetailId")
    @Mapping(target = "billID",source = "billID")
    BillDetail toEntity(BillDetailDTO billDetailDTO);
    @Mapping(target = "productDetailId",source = "productDetailId")
    @Mapping(target = "billID",source = "billID")
    BillDetailDTO toDto(BillDetail billDetail);

    List<BillDetailDTO> toDtos(List<BillDetail> datas);


}
