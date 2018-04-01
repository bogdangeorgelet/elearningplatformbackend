package com.elearningplatformservices.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InstructorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateCreated;

    public InstructorDto() {
    }
}
