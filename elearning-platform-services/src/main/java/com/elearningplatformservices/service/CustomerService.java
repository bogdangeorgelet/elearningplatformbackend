package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.CustomerDto;
import com.elearningplatformservices.entity.CustomerEntity;
import com.elearningplatformservices.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> getAll() {
        List<CustomerDto> allCustomers = new ArrayList<>();
        customerRepository.findAll().forEach(customerEntity -> {
            allCustomers.add(customerEntity.toDto());
        });
        return allCustomers;
    }

    public CustomerDto getOne(Long id) {
        CustomerEntity customerEntity = customerRepository.findOne(id);
        if (customerEntity != null)
            return customerEntity.toDto();
        else return null;
    }

    public void create(CustomerDto newCustomer) {
        CustomerEntity customerEntity = new CustomerEntity().update(newCustomer);
        customerRepository.save(customerEntity);
    }

    public void update(Long id, CustomerDto updatedCustomer) {
        CustomerEntity customerEntity = customerRepository.findOne(id);
        customerEntity.setUsername(updatedCustomer.getUsername());
        customerEntity.setPassword(updatedCustomer.getPassword());
        customerEntity.setFullName(updatedCustomer.getFullName());
        customerEntity.setEmail(updatedCustomer.getEmail());
        customerEntity.setAddress(updatedCustomer.getAddress());
        customerEntity.setPhoneNumber(updatedCustomer.getPhoneNumber());
        customerEntity.setCourses(updatedCustomer.getCourses());
        customerRepository.save(customerEntity);
    }

    public void delete(Long id) {
        customerRepository.delete(id);
    }
}
