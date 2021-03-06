package com.elearningplatformservices.entity;

import com.elearningplatformservices.dto.ContactUsCustomerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "CONTACTUSCUSTOMER")
public class ContactUsCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRSTNAME", unique = true)
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "MESSAGE")
    private String message;

    public ContactUsCustomerEntity() {
    }

    public ContactUsCustomerEntity(String firstName, String lastName, String email, String country, String phoneNumber, String message) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public ContactUsCustomerDto toDto() {
        ContactUsCustomerDto dto = new ContactUsCustomerDto();
        dto.setId(this.id);
        dto.setFirstName(this.firstName);
        dto.setLastName(this.lastName);
        dto.setEmail(this.email);
        dto.setCountry(this.country);
        dto.setPhoneNumber(this.phoneNumber);
        dto.setMessage(this.message);
        return dto;
    }

    public ContactUsCustomerEntity update(ContactUsCustomerDto contactUsCustomerDto) {
        this.id = contactUsCustomerDto.getId();
        this.firstName = contactUsCustomerDto.getFirstName();
        this.lastName = contactUsCustomerDto.getLastName();
        this.email = contactUsCustomerDto.getEmail();
        this.country = contactUsCustomerDto.getCountry();
        this.phoneNumber = contactUsCustomerDto.getPhoneNumber();
        this.message = contactUsCustomerDto.getMessage();
        return this;
    }


}
