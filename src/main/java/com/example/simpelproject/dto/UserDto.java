package com.example.simpelproject.dto;


import com.example.simpelproject.model.Image;
import com.example.simpelproject.model.Loaner;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Integer id;
    @NotBlank(message = "firstName name cannot be null or empty")
    private String firstName;
    @NotBlank(message = "lastName name cannot be null or empty")
    private String lastName;
    @NotBlank(message = "middleName name cannot be null or empty")
    private String middleName;
    @NotBlank(message = "userName name cannot be null or empty")
    private String userName;
    @NotBlank(message = "borrowName name cannot be null or empty")
    private String borrowName;
    @NotBlank(message = "phoneNumber name cannot be null or empty")
    private String phoneNumber;
    @NotBlank(message = "phoneNumber name cannot be null or empty")
    private String passportSeries;
    @NotBlank(message = "firstAddress name cannot be null or empty")
    private String firstAddress;
    @NotBlank(message = "monthlyPrice cannot be null or empty")
    private Double monthlyPrice;
    private LocalDate birthDate;
    private LocalDate workingTime;
    private LocalDate workingDays;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private LoanerDto loaners;
    private Integer loanersId;

    private ImageDto image;
    private Integer imageId;


}
