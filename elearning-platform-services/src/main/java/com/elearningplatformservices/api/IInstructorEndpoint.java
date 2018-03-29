package com.elearningplatformservices.api;

import com.elearningplatformservices.dto.InstructorDto;
import com.elearningplatformservices.entity.InstructorEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructors")
public interface IInstructorEndpoint {

    @GetMapping
    Iterable<InstructorEntity> getAll();

    @GetMapping("/{id}")
    ResponseEntity<Object> getOne(@PathVariable Long id);

    @PostMapping
    void addInstructor(@RequestBody InstructorDto instructorDto);

    @PutMapping("/{id}")
    void update(@PathVariable Long id, @RequestBody InstructorDto instructorDto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}
