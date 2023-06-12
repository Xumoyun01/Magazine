package com.example.simpelproject.service.mapper;

import com.example.simpelproject.dto.ImageDto;
import com.example.simpelproject.dto.ProductDto;
import com.example.simpelproject.model.Image;
import com.example.simpelproject.model.Product;
import com.example.simpelproject.service.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Autowired
    protected ImageService imageService;

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Product toEntity(ProductDto dto);

    @Mapping(target = "image", ignore = true)
    public abstract ProductDto toDtoNotImage(Product product);

    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "image", expression = "java(imageService.getImage(product.getImageId()).getData())")
    public abstract ProductDto toDto(Product product);

}
