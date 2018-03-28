package com.elearningplatformservices.entity;

import com.elearningplatformservices.dto.CustomerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;
    @OneToMany
    private List<CourseEntity> courses;

    public CustomerEntity(String username, String password, String fullName,
                          String email, String address, String phoneNumber, List<CourseEntity> courses) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.courses = courses;
    }

    public CustomerEntity() {
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }

    public CustomerDto toDto() {
        CustomerDto dto = new CustomerDto();
        dto.setId(this.id);
        dto.setUsername(this.username);
        dto.setPassword(this.password);
        dto.setAddress(this.address);
        dto.setEmail(this.email);
        dto.setPhoneNumber(this.phoneNumber);
        dto.setCourses(this.courses);
        return dto;
    }

}
