package com.example.simpelproject.controller;

import com.example.simpelproject.dto.ForeignDebtDto;
import com.example.simpelproject.dto.ImageDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.dto.UserDto;
import com.example.simpelproject.service.ImageService;
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
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @Operation(
            tags = "createImage",
            summary = "Your summary createImage image method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ImageDto.class
                            )
                    )
            )
    )
    @PostMapping(value = ("/create"))
    public ResponseDto<ImageDto> createImage(@Valid @RequestBody ImageDto dto) {
        return imageService.createImage(dto);
    }

    @Operation(
            tags = "getImage",
            summary = "Your summary getImage image method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ImageDto.class
                            )
                    )
            )
    )
    @GetMapping(value = ("/get"))
    public ResponseDto<ImageDto> getImage(@RequestParam Integer imageId) {
        return imageService.getImage(imageId);
    }

    @Operation(
            tags = "updateImage",
            summary = "Your summary updateImage image method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ImageDto.class
                            )
                    )
            )
    )
    @PutMapping(value = ("/update"))
    public ResponseDto<ImageDto> updateImage(@RequestParam Integer imageId,
                                           @RequestBody ImageDto dto) {
        return imageService.updateImage(dto, imageId);
    }

    @Operation(
            tags = "deleteImage",
            summary = "Your summary deleteImage image method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ImageDto.class
                            )
                    )
            )
    )
    @DeleteMapping(value = ("/delete"))
    public ResponseDto<ImageDto> deleteImage(@RequestParam Integer imageId) {
        return imageService.deleteImage(imageId);
    }

    @Operation(
            tags = "getAdventSearch",
            summary = "Your summary getAdventSearch image method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ImageDto.class
                            )
                    )
            )
    )
    @GetMapping(value = "/get-search")
    public ResponseDto<Page<ImageDto>> getAdventSearch(@RequestParam Map<String,String> params){
        return imageService.getAdventSearch(params);
    }

}
