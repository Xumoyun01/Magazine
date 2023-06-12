package com.example.simpelproject.dto;

import com.example.simpelproject.model.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportsDto {

    private Integer reportsId;
    @NotBlank(message = "prod name cannot be null or empty")
    private String prodName;
    @NotNull(message = "prodPresent name cannot be null")
    private Double prodPresent;
    private List<CategoryDto> categories;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
