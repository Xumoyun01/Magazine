package com.example.simpelproject.controller;

import com.example.simpelproject.dto.BasketDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.dto.UserDto;
import com.example.simpelproject.service.BasketService;
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
@RequestMapping("basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @Operation(
            tags = "createBasket",
            summary = "Your summary createBasket basket method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = BasketDto.class
                            )
                    )
            )
    )
    @PostMapping(value = ("/create"))
    public ResponseDto<BasketDto> createBasket(@Valid @RequestBody BasketDto dto) {
        return basketService.createBasket(dto);
    }

    @Operation(
            tags = "getBasket",
            summary = "Your summary getBasket basket method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = BasketDto.class
                            )
                    )
            )
    )
    @GetMapping(value = ("/get"))
    public ResponseDto<BasketDto> getBasket(@RequestParam Integer basketId) {
        return basketService.getBasket(basketId);
    }

    @Operation(
            tags = "updateBasket",
            summary = "Your summary updateBasket basket method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = BasketDto.class
                            )
                    )
            )
    )
    @PutMapping(value = ("/update"))
    public ResponseDto<BasketDto> updateBasket(@RequestParam Integer basketId,
                                                   @RequestBody BasketDto dto) {
        return basketService.updateBasket(dto, basketId);
    }

    @Operation(
            tags = "deleteBasket",
            summary = "Your summary deleteBasket basket method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = BasketDto.class
                            )
                    )
            )
    )
    @DeleteMapping(value = ("/delete"))
    public ResponseDto<BasketDto> deleteBasket(@RequestParam Integer basketId) {
        return basketService.deleteBasket(basketId);
    }

    @Operation(
            tags = "getAdventSearch",
            summary = "Your summary getAdventSearch basket method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = BasketDto.class
                            )
                    )
            )
    )
    @GetMapping(value = "/get-search")
    public ResponseDto<Page<BasketDto>> getAdventSearch(@RequestParam Map<String,String> params){
        return basketService.getAdventSearch(params);
    }
}
