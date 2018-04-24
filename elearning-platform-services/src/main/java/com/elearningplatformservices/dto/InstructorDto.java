package com.elearningplatformservices.dto;

import com.elearningplatformservices.entity.CourseEntity;
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

}
