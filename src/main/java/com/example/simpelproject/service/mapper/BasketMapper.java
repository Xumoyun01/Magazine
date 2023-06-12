package com.example.simpelproject.service.mapper;

import com.example.simpelproject.dto.BasketDto;
import com.example.simpelproject.model.Basket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class BasketMapper {
    @Autowired
    protected ProductMapper productMapper;

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Basket toEntity(BasketDto dto);

    @Mapping(target = "products",ignore = true)
    public abstract BasketDto toDtoNotProduct(Basket basket);

    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "products", expression = "java(basket.getProducts().stream().map(productMapper::toDto).collect(Collectors.toList()))")
    public abstract BasketDto toDto(Basket basket);


}
