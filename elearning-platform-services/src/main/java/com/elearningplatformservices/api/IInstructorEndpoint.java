package com.elearningplatformservices.api;

import com.elearningplatformservices.dto.InstructorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public interface IInstructorEndpoint {

    @GetMapping
    List<InstructorDto> getAll();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<Object> getOne(@PathVariable Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void addInstructor(@RequestBody InstructorDto instructorDto);

    @PutMapping("/{id}")
    void update(@PathVariable Long id, @RequestBody InstructorDto instructorDto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}
