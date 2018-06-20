package com.elearningplatformservices.dto;

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

}
