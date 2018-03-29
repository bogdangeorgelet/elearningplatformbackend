package com.elearningplatformservices.controller;


import com.elearningplatformservices.api.IInstructorEndpoint;
import com.elearningplatformservices.dto.InstructorDto;
import com.elearningplatformservices.entity.InstructorEntity;
import com.elearningplatformservices.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> getOne(@PathVariable Long id) {
        InstructorDto instructorDto = instructorService.getOneInstructor(id);
        if (instructorDto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("INSTRUCTOR NOT FOUND");
        return ResponseEntity.status(HttpStatus.OK).body(instructorDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addInstructor(@RequestBody InstructorDto instructorDto) {
        instructorService.addInstructor(instructorDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody InstructorDto instructorDto) {
        instructorService.updateInstructor(id, instructorDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        instructorService.deleteInstructor(id);
    }
}
