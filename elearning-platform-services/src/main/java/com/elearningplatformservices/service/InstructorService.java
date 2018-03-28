package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.InstructorDto;
import com.elearningplatformservices.entity.InstructorEntity;
import com.elearningplatformservices.repository.IInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class InstructorService {

    private IInstructorRepository IInstructorRepository;
    private Date date = new Date();

    @Autowired
    public InstructorService(IInstructorRepository IInstructorRepository) {
        this.IInstructorRepository = IInstructorRepository;
    }

    public Iterable<InstructorEntity> getAllInstructors() {
        Iterable<InstructorEntity> instructorDtos;
            instructorDtos = IInstructorRepository.findAll();
        return instructorDtos;
    }

    public InstructorDto getOneInstructor(Long id) {
        InstructorEntity instructorEntity = IInstructorRepository.findOne(id);
        if (instructorEntity != null)
            return instructorEntity.toDto();
        else
            return null;
    }

    public void addInstructor(InstructorDto instructorDto) {
        InstructorEntity instructorEntity = new InstructorEntity().toEntity(instructorDto);
        IInstructorRepository.save(instructorEntity);
    }

    public void updateInstructor(Long id, InstructorDto instructorDto) {
        InstructorEntity instructorEntity = IInstructorRepository.findOne(id);
        instructorEntity.setFirstName(instructorDto.getFirstName());
        instructorEntity.setLastName(instructorDto.getLastName());
        instructorEntity.setEmail(instructorDto.getEmail());
        instructorEntity.setPassword(instructorDto.getPassword());
        instructorEntity.setDateCreated(instructorDto.getDateCreated());
        IInstructorRepository.save(instructorEntity);
    }

    public void deleteInstructor(Long id) {
        IInstructorRepository.delete(id);
    }
}
