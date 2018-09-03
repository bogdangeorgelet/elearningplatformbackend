package com.elearningplatformservices.api;

import com.elearningplatformservices.dto.RegisterInstructorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registerInstructor")
public interface IRegisterInstructorEndpoint {

    @GetMapping
    List<RegisterInstructorDto> getAll();

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    ResponseEntity<String> create(@RequestBody RegisterInstructorDto newInstructor);

}
