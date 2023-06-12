package com.example.simpelproject.dto;

import com.example.simpelproject.model.Image;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDto {

    private Integer productId;
    @NotBlank(message = "prod name cannot be null or empty")
    private String prodName;
    @NotNull(message = "receivedPrice name cannot be null")
    private Double receivedPrice;
    @NotNull(message = "prodMass name cannot be null")
    private Double prodMass;
    @NotNull(message = "sellingPrice name cannot be null")
    private Double sellingPrice;
    @NotNull(message = "amount name cannot be null")
    private Double amount;


    private ImageDto image;

    private Integer imageId;


    private LocalDate expiredAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
