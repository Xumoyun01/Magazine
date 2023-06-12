package com.example.simpelproject.dto;

import com.example.simpelproject.model.Product;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class ForeignDebtDto {

    private Integer foreignId;
    @NotBlank(message = "companyName name cannot be null or empty")
    private String companyName;
    @NotBlank(message = "fullName name cannot be null or empty")
    private String fullName;
    @NotBlank(message = "firstPhoneNumber name cannot be null or empty")
    private String firstPhoneNumber;
    @NotBlank(message = "secondPhoneNumber name cannot be null or empty")
    private String secondPhoneNumber;
    private Boolean status;

    private List<ProductDto> products;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
