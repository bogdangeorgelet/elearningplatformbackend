package com.elearningplatformservices.entity;

import com.elearningplatformservices.dto.RegisterInstructorDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "REGISTERINSTRUCTOR")
public class RegisterInstructorEntity {

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

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CONFIRM_PASSWORD")
    private String confirmPassword;

    public RegisterInstructorEntity() {}

    public RegisterInstructorEntity(String firstName, String lastName, String email, String username, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public RegisterInstructorDto toDto() {
        RegisterInstructorDto dto = new RegisterInstructorDto();
        dto.setId(this.id);
        dto.setFirstName(this.firstName);
        dto.setLastName(this.lastName);
        dto.setEmail(this.email);
        dto.setUsername(this.username);
        dto.setPassword(this.password);
        dto.setConfirmPassword(this.confirmPassword);
        return dto;
    }

    public RegisterInstructorEntity update(RegisterInstructorDto registerInstructorDto) {
        this.id = registerInstructorDto.getId();
        this.firstName = registerInstructorDto.getFirstName();
        this.lastName = registerInstructorDto.getLastName();
        this.email = registerInstructorDto.getEmail();
        this.username = registerInstructorDto.getUsername();
        this.password = registerInstructorDto.getPassword();
        this.confirmPassword = registerInstructorDto.getConfirmPassword();
        return this;
    }
}
