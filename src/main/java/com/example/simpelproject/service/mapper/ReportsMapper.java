package com.example.simpelproject.service.mapper;

import com.example.simpelproject.dto.ReportsDto;
import com.example.simpelproject.model.Reports;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class})
public abstract class ReportsMapper {

    @Autowired
    protected CategoryMapper categoryMapper;

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Reports toEntity(ReportsDto dto);

    @Mapping(target = "categories",ignore = true)
    public abstract ReportsDto toDtoNotCategories(Reports reports);

    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "categories", expression = "java(reports.getCategories().stream().map(categoryMapper::toDto).collect(Collectors.toList()))")
    public abstract ReportsDto toDto(Reports reports);

}
