package com.example.simpelproject.dto;

import com.example.simpelproject.model.Basket;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class LoanerDto {

    private Integer loanerId;
    private Set<BasketDto> basket;
    @NotNull(message = "totalPrice name cannot be null")
    private Double totalPrice;
    private Boolean status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
