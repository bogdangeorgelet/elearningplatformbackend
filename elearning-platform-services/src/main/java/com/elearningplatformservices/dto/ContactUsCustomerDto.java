package com.elearningplatformservices.dto;

import com.elearningplatformservices.entity.ContactUsCustomerEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactUsCustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String phoneNumber;
    private String message;

    public ContactUsCustomerDto() {
    }

    public ContactUsCustomerEntity toEntity() {
        ContactUsCustomerEntity entity = new ContactUsCustomerEntity();
        entity.setId(this.id);
        entity.setFirstName(this.firstName);
        entity.setLastName(this.lastName);
        entity.setEmail(this.email);
        entity.setCountry(this.country);
        entity.setPhoneNumber(this.phoneNumber);
        entity.setMessage(this.message);
        return entity;
    }

}
