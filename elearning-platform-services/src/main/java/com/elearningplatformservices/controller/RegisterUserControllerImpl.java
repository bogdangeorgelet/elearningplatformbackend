package com.elearningplatformservices.controller;

import com.elearningplatformservices.api.IRegisterUserEndpoint;
import com.elearningplatformservices.dto.RegisterUserDto;
import com.elearningplatformservices.service.RegisterInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterUserControllerImpl implements IRegisterUserEndpoint {


    @Override
    public List<RegisterUserDto> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<String> create(RegisterUserDto newUser) {
        return null;
    }
}
