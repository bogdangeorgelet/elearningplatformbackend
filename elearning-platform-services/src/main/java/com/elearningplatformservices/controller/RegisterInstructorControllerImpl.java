package com.elearningplatformservices.controller;

import com.elearningplatformservices.api.IRegisterInstructorEndpoint;
import com.elearningplatformservices.dto.RegisterInstructorDto;
import com.elearningplatformservices.entity.RegisterInstructorEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RegisterInstructorControllerImpl implements IRegisterInstructorEndpoint {
    @Override
    public List<RegisterInstructorEntity> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<String> create(RegisterInstructorDto newInstructor) {
        return null;
    }
}
