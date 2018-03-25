package com.elearningplatformservices.controllers;

import com.elearningplatformservices.api.ICustomerEndpoint;
import com.elearningplatformservices.dto.CustomerDto;
import com.elearningplatformservices.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

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

    public ResponseEntity<CustomerDto> getOne(String username) {
        return customerService.getOne(username);
    }

    public String delete(String username) {
        return customerService.delete(username);
    }

    public String create(CustomerDto customerDto) {
        return customerService.create(customerDto);
    }

    public String update(String username, CustomerDto customerDto) {
        return customerService.update(username, customerDto);
    }
}
