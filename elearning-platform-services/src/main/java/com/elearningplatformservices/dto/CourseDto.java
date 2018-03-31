package com.elearningplatformservices.dto;

import com.elearningplatformservices.entity.CourseEntity;
import com.elearningplatformservices.entity.CustomerEntity;
import com.elearningplatformservices.entity.InstructorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
public class CourseDto {

    private Long id;
    private String name;
    private String course_type;
    private Double price;
    @JsonIgnore
    private CustomerEntity customer;

    public CourseDto() {
    }
}
