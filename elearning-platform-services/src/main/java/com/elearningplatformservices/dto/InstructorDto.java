package com.elearningplatformservices.dto;

import com.elearningplatformservices.entity.CourseEntity;
import com.elearningplatformservices.entity.InstructorEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class InstructorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @JsonIgnore
    private List<CourseEntity> courses;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateCreated;

    public InstructorDto() {
    }

    public InstructorEntity toEntity() {
        InstructorEntity entity = new InstructorEntity();
        entity.setId(this.id);
        entity.setFirstName(this.firstName);
        entity.setLastName(this.lastName);
        entity.setPassword(this.password);
        entity.setEmail(this.email);
        return entity;
    }

}
