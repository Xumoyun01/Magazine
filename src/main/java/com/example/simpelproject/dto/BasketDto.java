package com.example.simpelproject.dto;

import com.example.simpelproject.model.Product;
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
public class BasketDto {

    private Integer basketId;
    private List<ProductDto> products;
    @NotNull(message = "prodMass name cannot be null")
    private Double prodMass;
    @NotNull(message = "prodPrice name cannot be null")
    private Double prodPrice;
    @NotNull(message = "totalPrice name cannot be null")
    private Double totalPrice;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
