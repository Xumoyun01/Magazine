package com.example.simpelproject.service.validation;

import com.example.simpelproject.dto.ErrorDto;
import com.example.simpelproject.dto.UserDto;
import com.example.simpelproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidate {

    private final UserRepository userRepository;

    public List<ErrorDto> validate(UserDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (userRepository.existsByUserName(dto.getUserName())) {
            errors.add(new ErrorDto("userName", "userName is already exist"));
        }
        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            errors.add(new ErrorDto("phoneNumber", "Phone number is already exist."));
        }
        return errors;
    }
}
