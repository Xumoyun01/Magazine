package com.example.simpelproject.service.validation;

import com.example.simpelproject.dto.ErrorDto;
import com.example.simpelproject.dto.ImageDto;
import com.example.simpelproject.service.ImageService;
import com.example.simpelproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageValidate {

    private final UserService userService;

    public List<ErrorDto> validate(ImageDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        return errors;
    }
}
