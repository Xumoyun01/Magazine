package com.example.simpelproject.controller;

import com.example.simpelproject.dto.ImageDto;
import com.example.simpelproject.dto.LoanerDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.dto.UserDto;
import com.example.simpelproject.service.LoanerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("loaner")
public class LoanerController {

    private final LoanerService loanerService;

    @Operation(
            tags = "createLoaner",
            summary = "Your summary createLoaner loaner method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = LoanerDto.class
                            )
                    )
            )
    )
    @PostMapping(value = ("/create"))
    public ResponseDto<LoanerDto> createLoaner(@Valid @RequestBody LoanerDto dto) {
        return loanerService.createLoaner(dto);
    }

    @Operation(
            tags = "getLoaner",
            summary = "Your summary getLoaner loaner method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = LoanerDto.class
                            )
                    )
            )
    )
    @GetMapping(value = ("/get"))
    public ResponseDto<LoanerDto> getLoaner(@RequestParam Integer loanerId) {
        return loanerService.getLoaner(loanerId);
    }

    @Operation(
            tags = "updateLoaner",
            summary = "Your summary updateLoaner loaner method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = LoanerDto.class
                            )
                    )
            )
    )
    @PutMapping(value = ("/update"))
    public ResponseDto<LoanerDto> updateLoaner(@RequestParam Integer loanerId,
                                                   @RequestBody LoanerDto dto) {
        return loanerService.updateLoaner(dto, loanerId);
    }

    @Operation(
            tags = "deleteLoaner",
            summary = "Your summary deleteLoaner loaner method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = LoanerDto.class
                            )
                    )
            )
    )
    @DeleteMapping(value = ("/delete"))
    public ResponseDto<LoanerDto> deleteLoaner(@RequestParam Integer loanerId) {
        return loanerService.deleteLoaner(loanerId);
    }

    @Operation(
            tags = "getAdventSearch",
            summary = "Your summary getAdventSearch loaner method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = LoanerDto.class
                            )
                    )
            )
    )
    @GetMapping(value = "/get-search")
    public ResponseDto<Page<LoanerDto>> getAdventSearch(@RequestParam Map<String,String> params){
        return loanerService.getAdventSearch(params);
    }
}
