package com.elearningplatformservices.dto;

import com.elearningplatformservices.entity.CourseEntity;
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
    private List<CustomerEntity> customer;
    @JsonIgnore
    private InstructorEntity instructor;

    public CourseDto() {
    }
    
    public CourseEntity toEntity() {
        CourseEntity entity = new CourseEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setCourse_type(course_type);
        entity.setPrice(this.price);
        entity.setCategory(this.category);
        entity.setCustomer(this.customer);
        entity.setInstructor(this.instructor);
        return entity;
    }

}
