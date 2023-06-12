package com.example.simpelproject.service.validation;

import com.example.simpelproject.dto.EmployeesDto;
import com.example.simpelproject.dto.ErrorDto;
import com.example.simpelproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeesValidate {

    private final UserService userService;
    public List<ErrorDto> validate(EmployeesDto employeesDto) {
        List<ErrorDto>errorDtoList=new ArrayList<>();
        if (userService.getUser(employeesDto.getUserId()).getData()==null){
            errorDtoList.add(new ErrorDto("User",String.format("This is User %d id not found",employeesDto.getUserId())));
        }
        return errorDtoList;
    }
}
