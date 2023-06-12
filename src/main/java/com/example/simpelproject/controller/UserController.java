package com.example.simpelproject.controller;

import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.dto.UserDto;
import com.example.simpelproject.service.UserService;
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
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(method = "Create user",
            summary = "create new user",
            description = "create user")
    @PostMapping(value = ("/create"))
    public ResponseDto<UserDto> createUser(@Valid @RequestBody UserDto dto) {
        return userService.createUser(dto);
    }

    @Operation(
            tags = "get",
            summary = "Your summary get user method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserDto.class
                            )
                    )
            )
    )
    @GetMapping(value = ("/get"))
    public ResponseDto<UserDto> getUser(@RequestParam Integer userId) {
        return userService.getUser(userId);
    }

    @Operation(
            tags = "update",
            summary = "Your summary update user method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserDto.class
                            )
                    )
            )
    )
    @PutMapping(value = ("/update"))
    public ResponseDto<UserDto> updateUser(@RequestParam Integer userId,
                                           @RequestBody UserDto dto) {
        return userService.updateUser(dto, userId);
    }

    @Operation(tags = "Delete",
            summary = "Your summary delete by branch method.",
            description = "Your description this method.")
    @DeleteMapping(value = ("/delete"))
    public ResponseDto<UserDto> deleteUser(@RequestParam Integer userId) {
        return userService.deleteUser(userId);
    }

    @Operation(
            tags = "AdventSearch",
            summary = "Your summary AdventSearch user method.",
            description = "Your description this method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserDto.class
                            )
                    )
            )
    )
    @GetMapping(value = "/get-search")
    public ResponseDto<Page<UserDto>> getAdventSearch(@RequestParam Map<String,String> params){
        return userService.getAdventSearch(params);
    }

//    @GetMapping(value = ("/get-all"))
//    public ResponseDto<List<UserDto>> getAllUser() {
//        return userService.getAll();
//    }
}
