package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.RegisterUserDto;
import com.elearningplatformservices.entity.RegisterUserEntity;
import com.elearningplatformservices.repository.IRegisterUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterUserService {

    private final IRegisterUserRepository registerUserRepository;

    private static final Logger logger = LoggerFactory.getLogger(ContactUsCustomerService.class.getName());

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

    public ResponseEntity<String> create(RegisterUserDto registerUserDto) {
        try {
            RegisterUserEntity registerUserEntity = new RegisterUserEntity().update(registerUserDto);
            registerUserRepository.save(registerUserEntity);
        } catch (DataIntegrityViolationException e) {
            logger.info("--------------------------------------------------------- . \n " +
                    "Couldn't create, email is already taken: ");
            logger.info(registerUserDto.getEmail());
            logger.info("----------------------------------------------------------");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
