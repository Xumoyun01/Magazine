package com.example.simpelproject.controller;

import com.example.simpelproject.dto.BasketDto;
import com.example.simpelproject.dto.CategoryDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.dto.UserDto;
import com.example.simpelproject.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(
            tags = "createCategory",
            summary = "Your summary createCategory category method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CategoryDto.class
                            )
                    )
            )
    )
    @PostMapping(value = ("/create"))
    public ResponseDto<CategoryDto> createCategory(@Valid @RequestBody CategoryDto dto) {
        return categoryService.createCategory(dto);
    }

    @Operation(
            tags = "getCategory",
            summary = "Your summary getCategory category method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CategoryDto.class
                            )
                    )
            )
    )
    @GetMapping(value = ("/get"))
    public ResponseDto<CategoryDto> getCategory(@RequestParam Integer categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @Operation(
            tags = "updateCategory",
            summary = "Your summary updateCategory category method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CategoryDto.class
                            )
                    )
            )
    )
    @PutMapping(value = ("/update"))
    public ResponseDto<CategoryDto> updateCategory(@RequestParam Integer categoryId,
                                           @RequestBody CategoryDto dto) {
        return categoryService.updateCategory(dto, categoryId);
    }

    @Operation(
            tags = "deleteCategory",
            summary = "Your summary deleteCategory category method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CategoryDto.class
                            )
                    )
            )
    )
    @DeleteMapping(value = ("/delete"))
    public ResponseDto<CategoryDto> deleteCategory(@RequestParam Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @Operation(
            tags = "getAdventSearch",
            summary = "Your summary getAdventSearch category method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CategoryDto.class
                            )
                    )
            )
    )
    @GetMapping(value = "/get-search")
    public ResponseDto<Page<CategoryDto>> getAdventSearch(@RequestParam Map<String,String> params){
        return categoryService.getAdventSearch(params);
    }
}
