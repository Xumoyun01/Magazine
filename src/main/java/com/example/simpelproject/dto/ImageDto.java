package com.example.simpelproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDto {

    private Integer id;
    @NotBlank(message = "path name cannot be null or empty")
    private String path;
    @NotBlank(message = "type name cannot be null or empty")
    private String type;
    @NotNull(message = "size name cannot be null")
    private Integer size;
    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
