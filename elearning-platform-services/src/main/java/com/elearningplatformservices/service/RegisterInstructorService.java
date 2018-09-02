package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.RegisterInstructorDto;
import com.elearningplatformservices.entity.RegisterInstructorEntity;
import com.elearningplatformservices.repository.IRegisterInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterInstructorService {

    private final IRegisterInstructorRepository registerInstructorRepository;

    @Autowired
    public RegisterInstructorService(IRegisterInstructorRepository registerInstructorRepository) {
        this.registerInstructorRepository = registerInstructorRepository;
    }

    public List<RegisterInstructorDto> getAll() {
        List<RegisterInstructorDto> all = new ArrayList<>();
        registerInstructorRepository.findAll().forEach(registerInstructorEntity -> {
            all.add(registerInstructorEntity.toDto());
        });
        return all;
    }

//    public void create(RegisterInstructorDto newInstructor) {
//        if (newInstructor != null) {
//            RegisterInstructorEntity entity = new RegisterInstructorEntity().update(newInstructor);
//            registerInstructorRepository.save(entity);
//        }
//    }

    public ResponseEntity<String> create(RegisterInstructorDto newInstructor) {
        try {
            registerInstructorRepository.save(newInstructor.toEntity());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
