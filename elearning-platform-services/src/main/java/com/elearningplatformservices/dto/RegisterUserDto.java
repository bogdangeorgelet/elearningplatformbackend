package com.elearningplatformservices.dto;

import com.elearningplatformservices.entity.RegisterUserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;


    public RegisterUserEntity toEntity() {
        RegisterUserEntity userEntity = new RegisterUserEntity();
        userEntity.setId(this.id);
        userEntity.setFirstName(this.firstName);
        userEntity.setLastName(this.lastName);
        userEntity.setEmail(this.email);
        userEntity.setPassword(this.password);
        userEntity.setConfirmPassword(this.confirmPassword);
        return userEntity;
    }

}
