package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.InstructorDto;
import com.elearningplatformservices.entity.InstructorEntity;
import com.elearningplatformservices.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InstructorService {

    private InstructorRepository instructorRepository;
    private Date date = new Date();

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<InstructorDto> getAllInstructors() {
        List<InstructorDto> instructorDtos = new ArrayList<>();
        instructorRepository.findAll().forEach(userEntity -> {
            instructorDtos.add(userEntity.toDto());
        });
        return instructorDtos;
    }

    public InstructorDto getOneInstructor(Long id) {
        InstructorEntity instructorEntity = instructorRepository.findOne(id);
        if (instructorEntity != null)
            return instructorEntity.toDto();
        else
            return null;
    }

    public void addInstructor(InstructorDto instructorDto) {
        InstructorEntity instructorEntity = new InstructorEntity().toEntity(instructorDto);
        instructorDto.setPassword("xxx");
        instructorDto.setDateCreated(date);
        instructorRepository.save(instructorEntity);
    }

    public void updateInstructor(Long id, InstructorDto instructorDto) {
        InstructorEntity instructorEntity = instructorRepository.findOne(id);
        instructorEntity.setFirstName(instructorDto.getFirstName());
        instructorEntity.setLastName(instructorDto.getLastName());
        instructorEntity.setEmail(instructorDto.getEmail());
        instructorDto.setPassword(instructorDto.getPassword());
        instructorDto.setDateCreated(instructorDto.getDateCreated());
        instructorRepository.save(instructorEntity);
    }

    public void deleteInstructor(Long id) {
        instructorRepository.delete(id);
    }
}
