package com.elearningplatformservices.controller;

import com.elearningplatformservices.api.IRegisterInstructorEndpoint;
import com.elearningplatformservices.dto.RegisterInstructorDto;
import com.elearningplatformservices.entity.RegisterInstructorEntity;
import com.elearningplatformservices.service.RegisterInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@CrossOrigin
public class RegisterInstructorControllerImpl implements IRegisterInstructorEndpoint {

    private RegisterInstructorService registerInstructorService;

    @Autowired
    public RegisterInstructorControllerImpl(RegisterInstructorService registerInstructorService) {
        this.registerInstructorService = registerInstructorService;
    }

    public List<RegisterInstructorDto> getAll() {
        return registerInstructorService.getAll();
    }

    public ResponseEntity<String> create(@RequestBody RegisterInstructorDto newInstructor) {
        return registerInstructorService.create(newInstructor);
    }
}
