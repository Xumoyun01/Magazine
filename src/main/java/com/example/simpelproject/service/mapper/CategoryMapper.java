package com.example.simpelproject.service.mapper;

import com.example.simpelproject.dto.CategoryDto;
import com.example.simpelproject.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class CategoryMapper {

    @Autowired
    protected ProductMapper productMapper;

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Category toEntity(CategoryDto dto);

    @Mapping(target = "products",ignore = true)
    public abstract CategoryDto toDtoNotProduct(Category category);

    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "products", expression = "java(category.getProducts().stream().map(productMapper::toDto).collect(Collectors.toList()))")
    public abstract CategoryDto toDto(Category category);


}
