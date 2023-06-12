package com.example.simpelproject.controller;

import com.example.simpelproject.dto.CategoryDto;
import com.example.simpelproject.dto.EmployeesDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.dto.UserDto;
import com.example.simpelproject.service.EmployeesService;
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
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeesService employeesService;

    @Operation(
            tags = "createEmployees",
            summary = "Your summary createEmployees eEmployees method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = EmployeesDto.class
                            )
                    )
            )
    )
    @PostMapping(value = ("/create"))
    public ResponseDto<EmployeesDto> createEmployees(@Valid @RequestBody EmployeesDto dto) {
        return employeesService.createEmployees(dto);
    }

    @Operation(
            tags = "getEmployees",
            summary = "Your summary getEmployees eEmployees method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = EmployeesDto.class
                            )
                    )
            )
    )
    @GetMapping(value = ("/get"))
    public ResponseDto<EmployeesDto> getEmployees(@RequestParam Integer employeesId) {
        return employeesService.getEmployees(employeesId);
    }

    @Operation(
            tags = "updateEmployees",
            summary = "Your summary updateEmployees eEmployees method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = EmployeesDto.class
                            )
                    )
            )
    )
    @PutMapping(value = ("/update"))
    public ResponseDto<EmployeesDto> updateEmployees(@RequestParam Integer employeesId,
                                           @RequestBody EmployeesDto dto) {
        return employeesService.updateEmployees(dto, employeesId);
    }

    @Operation(
            tags = "deleteEmployees",
            summary = "Your summary deleteEmployees eEmployees method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = EmployeesDto.class
                            )
                    )
            )
    )
    @DeleteMapping(value = ("/delete"))
    public ResponseDto<EmployeesDto> deleteEmployees(@RequestParam Integer employeesId) {
        return employeesService.deleteEmployees(employeesId);
    }

    @Operation(
            tags = "getAdventSearch",
            summary = "Your summary getAdventSearch eEmployees method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = EmployeesDto.class
                            )
                    )
            )
    )
    @GetMapping(value = "/get-search")
    public ResponseDto<Page<EmployeesDto>> getAdventSearch(@RequestParam Map<String,String> params){
        return employeesService.getAdventSearch(params);
    }
}
