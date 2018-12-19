package com.elearningplatformservices.entity;

import com.elearningplatformservices.dto.RegisterUserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "REGISTERUSER")
public class RegisterUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CONFIRM_PASSWORD")
    private String confirmPassword;

    @JsonIgnore
    @Column(name = "ENABLED")
    private boolean enabled = true;

    public RegisterUserEntity() {
    }

    public RegisterUserEntity(String firstName, String lastName, String email, String username, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public RegisterUserDto toDto() {
        RegisterUserDto dto = new RegisterUserDto();
        dto.setId(this.id);
        dto.setFirstName(this.firstName);
        dto.setLastName(this.lastName);
        dto.setEmail(this.email);
        dto.setUsername(this.username);
        dto.setPassword(this.password);
        dto.setConfirmPassword(this.confirmPassword);
        return dto;
    }

    public RegisterUserEntity update(RegisterUserDto dto) {
        this.id = dto.getId();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.username = dto.getUsername();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.confirmPassword = dto.getConfirmPassword();
        return this;
    }


}
