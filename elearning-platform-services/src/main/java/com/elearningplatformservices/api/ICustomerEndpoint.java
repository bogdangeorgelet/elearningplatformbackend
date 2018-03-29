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

    @GetMapping("/{id}")
    CustomerDto getOne(@PathVariable Long id);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

    @PostMapping("/add")
    void create(@RequestBody CustomerDto customerDto);

    @PutMapping("/{id}")
    void update(@PathVariable Long id, @RequestBody CustomerDto customerDto);

}
