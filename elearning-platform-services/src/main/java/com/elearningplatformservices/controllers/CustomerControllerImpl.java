package com.elearningplatformservices.controllers;

import com.elearningplatformservices.api.ICustomerEndpoint;
import com.elearningplatformservices.dto.CustomerDto;
import com.elearningplatformservices.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    public CustomerDto getOne(@PathVariable Long id) {
        return customerService.getOne(id);
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
