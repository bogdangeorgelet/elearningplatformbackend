package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.InstructorDto;
import com.elearningplatformservices.entity.InstructorEntity;
import com.elearningplatformservices.repository.IInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorService {

    private IInstructorRepository instructorRepository;

    @Autowired
    public InstructorService(IInstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<InstructorDto> getAllInstructors() {
        List<InstructorDto> instructorDtos = new ArrayList<>();
        instructorRepository.findAll().forEach(instructorEntity -> {
            instructorDtos.add(instructorEntity.toDto());
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
        InstructorEntity instructorEntity = new InstructorEntity().update(instructorDto);
        instructorRepository.save(instructorEntity);
    }

    public void updateInstructor(Long id, InstructorDto instructorDto) {
        InstructorEntity instructorEntity = instructorRepository.findOne(id);
        instructorEntity.setFirstName(instructorDto.getFirstName());
        instructorEntity.setLastName(instructorDto.getLastName());
        instructorEntity.setEmail(instructorDto.getEmail());
        instructorEntity.setPassword(instructorDto.getPassword());
        instructorEntity.setDateCreated(instructorDto.getDateCreated());
        instructorRepository.save(instructorEntity);
    }

    public void deleteInstructor(Long id) {
        instructorRepository.delete(id);
    }
}
