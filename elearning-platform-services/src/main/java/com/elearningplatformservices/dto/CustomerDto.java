package com.elearningplatformservices.dto;

import com.elearningplatformservices.entity.CourseEntity;
import com.elearningplatformservices.entity.CustomerEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDto {

    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;

    public CustomerDto() {
    }

}
