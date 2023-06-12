package com.example.simpelproject.controller;

import com.example.simpelproject.dto.*;
import com.example.simpelproject.service.ReportsService;
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
@RequestMapping("reports")
public class ReportsController {

    private final ReportsService reportsService;

    @Operation(
            tags = "createReports",
            summary = "Your summary createReports reports method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ReportsDto.class
                            )
                    )
            )
    )
    @PostMapping(value = ("/create"))
    public ResponseDto<ReportsDto> createReports(@Valid @RequestBody ReportsDto dto) {
        return reportsService.createReports(dto);
    }

    @Operation(
            tags = "getReports",
            summary = "Your summary getReports reports method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ReportsDto.class
                            )
                    )
            )
    )
    @GetMapping(value = ("/get"))
    public ResponseDto<ReportsDto> getReports(@RequestParam Integer reportsId) {
        return reportsService.getReports(reportsId);
    }

    @Operation(
            tags = "updateReports",
            summary = "Your summary updateReports reports method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ReportsDto.class
                            )
                    )
            )
    )
    @PutMapping(value = ("/update"))
    public ResponseDto<ReportsDto> updateReports(@RequestParam Integer reportsId,
                                               @RequestBody ReportsDto dto) {
        return reportsService.updateReports(dto, reportsId);
    }

    @Operation(
            tags = "deleteReports",
            summary = "Your summary deleteReports reports method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ReportsDto.class
                            )
                    )
            )
    )
    @DeleteMapping(value = ("/delete"))
    public ResponseDto<ReportsDto> deleteReports(@RequestParam Integer reportsId) {
        return reportsService.deleteReports(reportsId);
    }

    @Operation(
            tags = "getAdventSearch",
            summary = "Your summary getAdventSearch reports method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ReportsDto.class
                            )
                    )
            )
    )
    @GetMapping(value = "/get-search")
    public ResponseDto<Page<ReportsDto>> getAdventSearch(@RequestParam Map<String,String> params){
        return reportsService.getAdventSearch(params);
    }
}
