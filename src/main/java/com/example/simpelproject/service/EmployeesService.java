package com.example.simpelproject.service;

import com.example.simpelproject.dto.EmployeesDto;
import com.example.simpelproject.dto.ErrorDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.model.Employees;
import com.example.simpelproject.repository.EmployeesRepository;
import com.example.simpelproject.service.mapper.EmployeesMapper;
import com.example.simpelproject.service.validation.EmployeesValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeesService {

    private final EmployeesMapper employeesMapper;
    private final EmployeesRepository employeesRepository;
    private final EmployeesValidate employeesValidate;


    public ResponseDto<EmployeesDto> createEmployees(EmployeesDto dto) {
        List<ErrorDto> errors = employeesValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Validate error!")
                    .data(dto)
                    .code(-2)
                    .errors(errors)
                    .build();
        }
        try {
            Employees employees = employeesMapper.toEntity(dto);
            employees.setCreatedAt(LocalDateTime.now());
            employeesRepository.save(employees);
            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .message(String.format("This is employees %d id successful created!",employees.getId()))
                    .data(employeesMapper.toDto(employees))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message(String.format("Employees while saving error :: %s", e.getMessage()))
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<EmployeesDto> getEmployees(Integer employeesId) {
        try {
            Optional<Employees> optional = employeesRepository.findByIdAndDeletedAtIsNull(employeesId);
            if (optional.isEmpty()) {
                return ResponseDto.<EmployeesDto>builder()
                        .message("Employees is not found!")
                        .code(-1)
                        .build();
            }
            return ResponseDto.<EmployeesDto>builder()
                    .message("OK")
                    .success(true)
                    .data(employeesMapper.toDto(optional.get()))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<EmployeesDto>builder()
                    .code(-3)
                    .message("Database Error: " + c.getMessage())
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<EmployeesDto> updateEmployees(EmployeesDto dto, Integer employeesId) {
        Optional<Employees> optional = employeesRepository.findByIdAndDeletedAtIsNull(employeesId);
        if (optional.isEmpty()) {
            return ResponseDto.<EmployeesDto>builder()
                    .message(String.format("This is employees %d id not found!",employeesId))
                    .code(-1)
                    .build();
        }
        List<ErrorDto> errors = employeesValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Validate error!")
                    .data(dto)
                    .code(-2)
                    .errors(errors)
                    .build();
        }
        try {
            Employees employees = employeesMapper.toEntity(dto);
            employees.setUpdatedAt(LocalDateTime.now());
            employees.setCreatedAt(optional.get().getCreatedAt());
            employees.setDeletedAt(optional.get().getDeletedAt());
            employeesRepository.save(employees);
            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .message(String.format("This is employees %d id successful updated!",employees.getId()))
                    .data(employeesMapper.toDto(employees))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message(String.format("Employees while saving error :: %s", e.getMessage()))
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<EmployeesDto> deleteEmployees(Integer employeesId) {
        Optional<Employees> optional = employeesRepository.findByIdAndDeletedAtIsNull(employeesId);
        if (optional.isEmpty()) {
            return ResponseDto.<EmployeesDto>builder()
                    .message(String.format("This is employees %d id not found!",employeesId))
                    .code(-1)
                    .build();
        }
        try {
            Employees employees = optional.get();
            employees.setDeletedAt(LocalDateTime.now());
            employeesRepository.save(employees);
            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .message(String.format("This is employees %d id successful deleted!",employees.getId()))
                    .data(employeesMapper.toDto(employees))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message(String.format("Orders while saving error :: %s", e.getMessage()))
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<Page<EmployeesDto>> getAdventSearch(Map<String, String> params) {
        return null;
    }
}
