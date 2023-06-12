package com.example.simpelproject.service.mapper;

import com.example.simpelproject.dto.EmployeesDto;
import com.example.simpelproject.model.Employees;
import com.example.simpelproject.repository.UserRepository;
import com.example.simpelproject.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class EmployeesMapper {

    @Autowired
    protected UserService userService;


    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Employees toEntity(EmployeesDto employeesDto);

    @Mapping(target = "users",ignore = true)
    public abstract EmployeesDto toDtoNotUser(Employees employees);
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "users", expression = "java(userService.getUser(employees.getUserId()).getData())")
    public abstract EmployeesDto toDto(Employees employees);


}
