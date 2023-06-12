package com.example.simpelproject.service.mapper;


import com.example.simpelproject.dto.ForeignDebtDto;
import com.example.simpelproject.model.ForeignDebt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.Collator;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class})
public abstract class ForeignDebtMapper {

    @Autowired
    protected ProductMapper productMapper;

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract ForeignDebt toEntity(ForeignDebtDto dto);

    @Mapping(target = "products", ignore = true)
    public abstract ForeignDebtDto toDtoNotProduct(ForeignDebt foreignDebt);

    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "products", expression = "java(foreignDebt.getProducts().stream().map(productMapper::toDto).collect(Collectors.toList()))")
    public abstract ForeignDebtDto toDto(ForeignDebt foreignDebt);


}
