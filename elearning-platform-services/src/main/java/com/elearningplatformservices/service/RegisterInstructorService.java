package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.RegisterInstructorDto;
import com.elearningplatformservices.entity.RegisterInstructorEntity;
import com.elearningplatformservices.repository.IRegisterInstructorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterInstructorService {

    private final IRegisterInstructorRepository registerInstructorRepository;

    private static final Logger logger = LoggerFactory.getLogger(ContactUsCustomerService.class.getName());

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

    public ResponseEntity<String> create(RegisterInstructorDto newInstructor) {
        try {
            RegisterInstructorEntity registerInstructorEntity = new RegisterInstructorEntity().update(newInstructor);
            registerInstructorRepository.save(registerInstructorEntity);
        } catch (DataIntegrityViolationException e) {
            logger.info("--------------------------------------------------------- . \n " +
                    "Couldn't create, username is already taken: ");
            logger.info(newInstructor.getUsername());
            logger.info("----------------------------------------------------------");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
