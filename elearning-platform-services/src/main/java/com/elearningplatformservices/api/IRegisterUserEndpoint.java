package com.elearningplatformservices.api;

import com.elearningplatformservices.dto.RegisterUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registerUser")
public interface IRegisterUserEndpoint {

    @GetMapping
    List<RegisterUserDto> getAll();

    @PostMapping("/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<String> create(@RequestBody RegisterUserDto newUser);

}
