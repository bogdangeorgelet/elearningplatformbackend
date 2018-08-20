package com.elearningplatformservices.dto;


import com.elearningplatformservices.entity.RegisterInstructorEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterInstructorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    public RegisterInstructorEntity toEntity() {
        RegisterInstructorEntity entity = new RegisterInstructorEntity();
        entity.setId(this.id);
        entity.setFirstName(this.firstName);
        entity.setLastName(this.lastName);
        entity.setEmail(this.email);
        entity.setUsername(this.username);
        entity.setPassword(this.password);
        entity.setConfirmPassword(this.confirmPassword);
        return entity;
    }

}
