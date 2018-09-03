package com.elearningplatformservices.api;

import com.elearningplatformservices.dto.ContactUsCustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactUs")
public interface IContactUsCustomerEndpoint {

    @GetMapping
    List<ContactUsCustomerDto> getAll();

    @PostMapping("/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody ContactUsCustomerDto newCustomer);

}
