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
@Table(name = "CUSTOMERS")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FULLNAME")
    private String fullName;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
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

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
