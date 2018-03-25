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

    @JsonIgnore
    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    @JsonIgnore
    private List<CourseEntity> courses;

    public CustomerDto(String fullName, String username, String password,
                       String email, String address, String phoneNumber) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public CustomerDto() {
    }

    public CustomerEntity toEntity() {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(this.id);
        entity.setFullName(this.fullName);
        entity.setUsername(this.username);
        entity.setPassword(this.password);
        entity.setEmail(this.email);
        entity.setAddress(this.address);
        entity.setPhoneNumber(this.phoneNumber);
        entity.setCourses(this.courses);
        return entity;
    }

    public void update(CustomerDto dto) {
        this.fullName = dto.getFullName();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.address = dto.getAddress();
        this.phoneNumber = dto.getPhoneNumber();
        this.courses = dto.getCourses();
    }

}
