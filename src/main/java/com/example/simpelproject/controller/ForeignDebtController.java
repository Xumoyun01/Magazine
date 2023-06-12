package com.example.simpelproject.controller;

import com.example.simpelproject.dto.EmployeesDto;
import com.example.simpelproject.dto.ForeignDebtDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.dto.UserDto;
import com.example.simpelproject.service.ForeignDebtService;
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
@RequestMapping("foreignDebt")
@RequiredArgsConstructor
public class ForeignDebtController {

    private final ForeignDebtService foreignDebtService;

    @Operation(
            tags = "createForeignDebt",
            summary = "Your summary createForeignDebt foreignDebt method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ForeignDebtDto.class
                            )
                    )
            )
    )
    @PostMapping(value = ("/create"))
    public ResponseDto<ForeignDebtDto> createForeignDebt(@Valid @RequestBody ForeignDebtDto dto) {
        return foreignDebtService.createForeignDebt(dto);
    }

    @Operation(
            tags = "getForeignDebt",
            summary = "Your summary getForeignDebt foreignDebt method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ForeignDebtDto.class
                            )
                    )
            )
    )
    @GetMapping(value = ("/get"))
    public ResponseDto<ForeignDebtDto> getForeignDebt(@RequestParam Integer foreignDebtId) {
        return foreignDebtService.getForeignDebt(foreignDebtId);
    }

    @Operation(
            tags = "updateForeignDebt",
            summary = "Your summary updateForeignDebt foreignDebt method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ForeignDebtDto.class
                            )
                    )
            )
    )
    @PutMapping(value = ("/update"))
    public ResponseDto<ForeignDebtDto> updateForeignDebt(@RequestParam Integer foreignDebtId,
                                                   @RequestBody ForeignDebtDto dto) {
        return foreignDebtService.updateForeignDebt(dto, foreignDebtId);
    }

    @Operation(
            tags = "deleteForeignDebt",
            summary = "Your summary deleteForeignDebt foreignDebt method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ForeignDebtDto.class
                            )
                    )
            )
    )
    @DeleteMapping(value = ("/delete"))
    public ResponseDto<ForeignDebtDto> deleteForeignDebt(@RequestParam Integer foreignDebtId) {
        return foreignDebtService.deleteForeignDebt(foreignDebtId);
    }

    @Operation(
            tags = "getAdventSearch",
            summary = "Your summary getAdventSearch foreignDebt method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ForeignDebtDto.class
                            )
                    )
            )
    )
    @GetMapping(value = "/get-search")
    public ResponseDto<Page<ForeignDebtDto>> getAdventSearch(@RequestParam Map<String,String> params){
        return foreignDebtService.getAdventSearch(params);
    }
}
