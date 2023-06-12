package com.example.simpelproject.service;

import com.example.simpelproject.dto.ErrorDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.dto.UserDto;
import com.example.simpelproject.model.User;
import com.example.simpelproject.repository.UserRepository;
import com.example.simpelproject.repository.UserRepositoryImpl;
import com.example.simpelproject.service.mapper.UserMapper;
import com.example.simpelproject.service.validation.UserValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final UserValidate userValidate;

    private final UserRepositoryImpl userRepositoryImpl;

    public ResponseDto<UserDto> createUser(UserDto dto) {
        List<ErrorDto> errors = userValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .errors(errors)
                    .code(-2)
                    .build();
        }
        try {
            User user = userMapper.toEntity(dto);
            user.setCreatedAt(LocalDateTime.now());
            userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("User successful created!")
                    .data(userMapper.toDtoByNotImage(user))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("User while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<UserDto> getUser(Integer userId) {
        Optional<User> optional = userRepository.findByIdAndDeletedAtIsNull(userId);
        if (optional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("User is not found!")
                    .code(-3)
                    .build();
        }
        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("OK")
                .data(userMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<UserDto> updateUser(UserDto dto, Integer userId) {
        List<ErrorDto> errors = userValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .errors(errors)
                    .code(-2)
                    .build();
        }

        Optional<User> optional = userRepository.findByIdAndDeletedAtIsNull(userId);
        if (optional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("User is not found!")
                    .code(-3)
                    .build();
        }

        try {
            User user = userMapper.toEntity(dto);
            user.setId(optional.get().getId());
            user.setCreatedAt(optional.get().getCreatedAt());
            user.setDeletedAt(optional.get().getDeletedAt());
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("User successful updated!")
                    .code(0)
                    .data(userMapper.toDtoByNotImage(user))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("User while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<UserDto> deleteUser(Integer userId) {
        Optional<User> optional = userRepository.findByIdAndDeletedAtIsNull(userId);
        if (optional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("User is not found!")
                    .code(-3)
                    .build();
        }
        try {
            User user = optional.get();
            user.setDeletedAt(LocalDateTime.now());
            userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .message("User successful deleted!")
                    .success(true)
                    .data(userMapper.toDtoByNotImage(user))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("User while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<Page<UserDto>> getAdventSearch(Map<String, String> params) {
        Page<UserDto> user=userRepositoryImpl.getAdvancedSearch(params).map(userMapper::toDto);
        return ResponseDto.<Page<UserDto>>builder()
                .message("ok")
                .code(0)
                .data(user)
                .build();
    }
}
