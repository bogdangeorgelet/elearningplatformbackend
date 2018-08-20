package com.elearningplatformservices.api;

import com.elearningplatformservices.dto.RegisterInstructorDto;
import com.elearningplatformservices.entity.RegisterInstructorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registerInstructor")
public interface IRegisterInstructorEndpoint {

    @GetMapping
    List<RegisterInstructorEntity> getAll();

    @PostMapping("/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<String> create(@RequestBody RegisterInstructorDto newInstructor);

}
