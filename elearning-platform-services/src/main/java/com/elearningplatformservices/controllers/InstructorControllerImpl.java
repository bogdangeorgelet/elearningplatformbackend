package com.elearningplatformservices.controllers;


import com.elearningplatformservices.api.IInstructorEndpoint;
import com.elearningplatformservices.dto.InstructorDto;
import com.elearningplatformservices.entity.InstructorEntity;
import com.elearningplatformservices.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public class InstructorControllerImpl implements IInstructorEndpoint {

    private InstructorService instructorService;

    @Autowired
    public InstructorControllerImpl(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public Iterable<InstructorEntity> getAll() {
        return instructorService.getAllInstructors();
    }

    @GetMapping("/{id}")
    public InstructorDto getOne(@PathVariable Long id) {
        return instructorService.getOneInstructor(id);
    }

    @PostMapping
    public void addInstructor(@RequestBody InstructorDto instructorDto){
        instructorService.addInstructor(instructorDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,@RequestBody InstructorDto instructorDto) {
        instructorService.updateInstructor(id, instructorDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        instructorService.deleteInstructor(id);
    }
}
