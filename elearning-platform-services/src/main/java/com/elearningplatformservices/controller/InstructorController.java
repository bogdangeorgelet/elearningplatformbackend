package com.elearningplatformservices.controller;


import com.elearningplatformservices.dto.InstructorDto;
import com.elearningplatformservices.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<InstructorDto> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    @GetMapping(value = "/{id}")
    public InstructorDto getOneInstructor(@PathVariable Long id) {
        return instructorService.getOneInstructor(id);
    }

    @PostMapping
    public void addInstructor(@RequestBody InstructorDto instructorDto){
        instructorService.addInstructor(instructorDto);
    }

    @PutMapping("/{id}")
    public void updateInstructor(@PathVariable Long id, @RequestBody InstructorDto instructorDto) {
        instructorService.updateInstructor(id, instructorDto);
    }

    @DeleteMapping("/{id}")
    public void deleteInstructor(@PathVariable("id") Long id) {
        instructorService.deleteInstructor(id);
    }
}
