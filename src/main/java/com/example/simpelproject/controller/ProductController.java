package com.example.simpelproject.controller;

import com.example.simpelproject.dto.LoanerDto;
import com.example.simpelproject.dto.ProductDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.dto.UserDto;
import com.example.simpelproject.service.ProductService;
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
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(
            tags = "createProduct",
            summary = "Your summary createProduct product method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProductDto.class
                            )
                    )
            )
    )
    @PostMapping(value = ("/create"))
    public ResponseDto<ProductDto> createProduct(@Valid @RequestBody ProductDto dto) {
        return productService.createProduct(dto);
    }

    @Operation(
            tags = "getProduct",
            summary = "Your summary getProduct product method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProductDto.class
                            )
                    )
            )
    )
    @GetMapping(value = ("/get"))
    public ResponseDto<ProductDto> getProduct(@RequestParam Integer productId) {
        return productService.getProduct(productId);
    }

    @Operation(
            tags = "updateProduct",
            summary = "Your summary updateProduct product method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProductDto.class
                            )
                    )
            )
    )
    @PutMapping(value = ("/update"))
    public ResponseDto<ProductDto> updateProduct(@RequestParam Integer productId,
                                                   @RequestBody ProductDto dto) {
        return productService.updateProduct(dto, productId);
    }

    @Operation(
            tags = "deleteProduct",
            summary = "Your summary deleteProduct product method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProductDto.class
                            )
                    )
            )
    )
    @DeleteMapping(value = ("/delete"))
    public ResponseDto<ProductDto> deleteProduct(@RequestParam Integer productId) {
        return productService.deleteProduct(productId);
    }

    @Operation(
            tags = "getAdventSearch",
            summary = "Your summary getAdventSearch product method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProductDto.class
                            )
                    )
            )
    )
    @GetMapping(value = "/get-search")
    public ResponseDto<Page<ProductDto>> getAdventSearch(@RequestParam Map<String,String> params){
        return productService.getAdventSearch(params);
    }


}
