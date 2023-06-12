package com.example.simpelproject.dto;

import com.example.simpelproject.model.Product;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;
    @NotBlank(message = "categoryName name cannot be null or empty")
    private String categoryName;

    private List<ProductDto> products;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
