package com.example.simpelproject.service.mapper;

import com.example.simpelproject.dto.LoanerDto;
import com.example.simpelproject.model.Loaner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class LoanerMapper {

    @Autowired
    protected BasketMapper basketMapper;

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Loaner toEntity(LoanerDto dto);

    @Mapping(target = "basket", ignore = true)
    public abstract LoanerDto toDtoNotBasket(Loaner loaner);
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "basket", expression = "java(loaner.getBaskets().stream().map(basketMapper::toDto).collect(Collectors.toSet()))")
    public abstract LoanerDto toDto(Loaner loaner);

}
