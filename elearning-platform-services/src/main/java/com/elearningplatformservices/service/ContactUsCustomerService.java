package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.ContactUsCustomerDto;
import com.elearningplatformservices.entity.ContactUsCustomerEntity;
import com.elearningplatformservices.repository.IContactUsCustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactUsCustomerService {

    private final IContactUsCustomerRepository contactUsCustomerRepository;

    @Autowired
    public ContactUsCustomerService(IContactUsCustomerRepository contactUsCustomerRepository) {
        this.contactUsCustomerRepository = contactUsCustomerRepository;
    }

    public List<ContactUsCustomerDto> getAll() {
        List<ContactUsCustomerDto> allCustomers = new ArrayList<>();
        contactUsCustomerRepository.findAll().forEach(contactUsCustomerEntity -> {
            allCustomers.add(contactUsCustomerEntity.toDto());
        });
        return allCustomers;
    }


    public void create(@RequestBody ContactUsCustomerDto newCustomer) {
        if (newCustomer != null && this.contactUsCustomerRepository.findByFirstName(newCustomer.getFirstName()) == null) {
            ContactUsCustomerEntity contactUsCustomerEntity = new ContactUsCustomerEntity().update(newCustomer);
            contactUsCustomerRepository.save(contactUsCustomerEntity);
        }
    }

}
