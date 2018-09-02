package com.elearningplatformservices.controller;

import com.elearningplatformservices.api.IRegisterUserEndpoint;
import com.elearningplatformservices.dto.RegisterUserDto;
import com.elearningplatformservices.service.RegisterInstructorService;
import com.elearningplatformservices.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@CrossOrigin
public class RegisterUserControllerImpl implements IRegisterUserEndpoint {

    private RegisterUserService registerUserService;

    @Autowired
    public RegisterUserControllerImpl(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    public List<RegisterUserDto> getAll() {
        return registerUserService.getAll();
    }

    public ResponseEntity<String> create(@RequestBody RegisterUserDto newUser) {
        return registerUserService.create(newUser);
    }
}
