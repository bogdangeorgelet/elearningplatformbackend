package com.elearningplatformservices.dto;

import com.elearningplatformservices.entity.CustomerEntity;
import com.elearningplatformservices.entity.InstructorEntity;
import com.elearningplatformservices.enums.CourseCategories;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CourseDto {

    private Long id;
    private String name;
    private String course_type;
    private Double price;
    private CourseCategories category;
    @JsonIgnore
//    private CustomerEntity customer;
    private List<CustomerEntity> customer;
    @JsonIgnore
    private InstructorEntity instructor;

    public CourseDto() {
    }
}
