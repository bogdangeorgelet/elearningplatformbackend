package com.elearningplatformservices.entity;

import com.elearningplatformservices.dto.CustomerDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;

    @ManyToMany(mappedBy = "customer")
    private List<CourseEntity> courses;

    public CustomerEntity() {
    }

    public CustomerDto toDto() {
        CustomerDto dto = new CustomerDto();
        dto.setId(this.id);
        dto.setUsername(this.username);
        dto.setPassword(this.password);
        dto.setFullName(this.fullName);
        dto.setAddress(this.address);
        dto.setEmail(this.email);
        dto.setPhoneNumber(this.phoneNumber);
        dto.setCourses(this.courses);
        return dto;
    }

    public CustomerEntity update(CustomerDto customerDto) {
        this.id = customerDto.getId();
        this.username = customerDto.getUsername();
        this.password = customerDto.getPassword();
        this.fullName = customerDto.getFullName();
        this.email = customerDto.getEmail();
        this.address = customerDto.getAddress();
        this.phoneNumber = customerDto.getPhoneNumber();
        this.courses = customerDto.getCourses();
        return this;
    }
}
