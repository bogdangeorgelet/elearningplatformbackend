package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.CustomerDto;
import com.elearningplatformservices.entity.CustomerEntity;
import com.elearningplatformservices.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> getAll() {
        List<CustomerEntity> allCustomers = (List<CustomerEntity>) this.customerRepository.findAll();
        return allCustomers.stream()
                .map(CustomerEntity::toDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<CustomerDto> getOne(@PathVariable (value = "username") String username) {
        if (this.customerRepository.findByUsername(username)!= null) {
            return new ResponseEntity<>(this.customerRepository.findByUsername(username).toDto(),
                    HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public String create(@RequestBody CustomerDto customerDto) {
        if (customerDto != null && this.customerRepository.findByUsername(customerDto.getUsername()) != null) {
            this.customerRepository.save(customerDto.toEntity());
            return "Created";
        } else
            return "Couldn't create";
    }

    public String delete(@PathVariable(value = "username") String username)  {
        CustomerEntity entity = this.customerRepository.findByUsername(username);
        if (entity != null) {
            this.customerRepository.delete(entity);
            return "Deleted";
        } else
            return "Couldn't Delete";
    }

    public String update(@PathVariable String username, @RequestBody CustomerDto customerDto) {
        if (this.customerRepository.findByUsername(username) != null) {
            CustomerDto dto = this.customerRepository.findByUsername(username).toDto();
            if (customerDto.getUsername().equals(dto.getUsername())) {
                dto.update(customerDto);
                this.customerRepository.save(dto.toEntity());
                return "updated";
            } else {
                List<CustomerEntity> all = (List<CustomerEntity>) this.customerRepository.findAll();
                if (all.stream().anyMatch(un -> un.getUsername().equals(dto.getUsername()))) {
                    return "Couldn't Update";
                } else {
                    dto.update(customerDto);
                    this.customerRepository.save(dto.toEntity());
                    return "Updated";
                }
            }
        } else
            return "Couldn't update";
    }
}
