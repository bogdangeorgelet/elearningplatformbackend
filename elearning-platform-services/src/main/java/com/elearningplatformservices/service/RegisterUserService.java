package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.RegisterUserDto;
import com.elearningplatformservices.repository.IRegisterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterUserService {

    private final IRegisterUserRepository registerUserRepository;

    @Autowired
    public RegisterUserService(IRegisterUserRepository registerUserRepository) {
        this.registerUserRepository = registerUserRepository;
    }

    public List<RegisterUserDto> getAll() {
        List<RegisterUserDto> all = new ArrayList<>();
        registerUserRepository.findAll().forEach(registerUserEntity -> {
            all.add(registerUserEntity.toDto());
        });
        return all;
    }

    public ResponseEntity<String> create(@RequestBody RegisterUserDto registerUserDto) {
        try {
            registerUserRepository.save(registerUserDto.toEntity());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
