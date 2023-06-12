package com.example.simpelproject.service.mapper;

import com.example.simpelproject.dto.UserDto;
import com.example.simpelproject.model.User;
import com.example.simpelproject.service.ImageService;
import com.example.simpelproject.service.LoanerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;


import java.util.stream.Collectors;
@Mapper(componentModel = "spring", imports = {Collectors.class})
public abstract class UserMapper {

    @Lazy
    @Autowired
    protected ImageService imageService;

    @Lazy
    @Autowired
    protected LoanerService loanerService;



    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract User toEntity(UserDto dto);

    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "image", expression = "java(imageService.getImage(user.getImageId()).getData())")
    @Mapping(target = "loaners", expression = "java(loanerService.getLoaner(user.getLoanersId()).getData())")
    public abstract UserDto toDto(User user);


    @Mapping(target = "image",ignore = true)
    @Mapping(target = "loaners",ignore = true)
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    public abstract UserDto toDtoByNotImage(User user);



}
