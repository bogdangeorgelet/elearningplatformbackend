package com.elearningplatformservices.controller;

import com.elearningplatformservices.api.ICustomerEndpoint;
import com.elearningplatformservices.dto.CustomerDto;
import com.elearningplatformservices.entity.CustomerEntity;
import com.elearningplatformservices.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@CrossOrigin
public class CustomerControllerImpl implements ICustomerEndpoint{

    private CustomerService customerService;

    @Autowired
    public CustomerControllerImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<CustomerDto> getAll() {
        return customerService.getAll();
    }

    public ResponseEntity<CustomerDto> getOne(@PathVariable Long id) {
        CustomerDto customerDto = customerService.getOne(id);
        if (customerDto != null)
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

    public void create(@RequestBody CustomerDto customerDto) {
        customerService.create(customerDto);
    }

    public void update(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        customerService.update(id, customerDto);
    }
}
