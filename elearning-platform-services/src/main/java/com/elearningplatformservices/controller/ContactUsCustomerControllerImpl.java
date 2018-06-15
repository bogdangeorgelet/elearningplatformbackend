package com.elearningplatformservices.controller;

import com.elearningplatformservices.api.IContactUsCustomerEndpoint;
import com.elearningplatformservices.dto.ContactUsCustomerDto;
import com.elearningplatformservices.service.ContactUsCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Component
@CrossOrigin
public class ContactUsCustomerControllerImpl implements IContactUsCustomerEndpoint {

    private ContactUsCustomerService contactUsCustomerService;

    @Autowired
    public ContactUsCustomerControllerImpl(ContactUsCustomerService contactUsCustomerService) {
        this.contactUsCustomerService = contactUsCustomerService;
    }

    @Override
    public List<ContactUsCustomerDto> getAll() {
        return contactUsCustomerService.getAll();
    }

    public void create(@RequestBody ContactUsCustomerDto newCustomer) {
        contactUsCustomerService.create(newCustomer);
    }
}
