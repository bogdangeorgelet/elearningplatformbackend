package com.elearningplatformservices.dto;

import com.elearningplatformservices.entity.CustomerEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


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
