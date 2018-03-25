package com.elearningplatformservices.api;

import com.elearningplatformservices.dto.CustomerDto;
import com.elearningplatformservices.entity.CustomerEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public interface ICustomerEndpoint {

    @GetMapping
    List<CustomerDto> getAll();

    @GetMapping(path = "/{username}")
    ResponseEntity<CustomerDto> getOne(String username);

    @DeleteMapping(path = "/{username}")
    String delete(String username);

    @PostMapping(path = "/add")
    String create(CustomerDto customerDto);

    @PutMapping(path = "/{username}")
    String update(String username, CustomerDto customerDto);

}
