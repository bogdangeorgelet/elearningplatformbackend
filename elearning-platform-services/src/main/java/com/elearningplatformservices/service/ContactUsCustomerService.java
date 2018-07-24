package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.ContactUsCustomerDto;
import com.elearningplatformservices.entity.ContactUsCustomerEntity;
import com.elearningplatformservices.repository.IContactUsCustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactUsCustomerService {

    private final IContactUsCustomerRepository contactUsCustomerRepository;

    private static final Logger logger = LoggerFactory.getLogger(ContactUsCustomerService.class.getName());

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

    public String create(ContactUsCustomerDto newCustomer) {
        if (newCustomer != null &&
                this.contactUsCustomerRepository.findByEmail(newCustomer.getEmail()) == null) {
            ContactUsCustomerEntity contactUsCustomerEntity = new ContactUsCustomerEntity().update(newCustomer);
            contactUsCustomerRepository.save(contactUsCustomerEntity);
            return "Created";
        } else {
            logger.info("----------------------------------------------------------");
            logger.info("Couldn't create, email is already taken: ");
            logger.info(newCustomer.getEmail());
            logger.info("----------------------------------------------------------");
            return "Couldn't create, email is already taken:";
        }
    }

}
