package com.elearningplatformservices.dto;

import com.elearningplatformservices.entity.CourseEntity;
import com.elearningplatformservices.entity.InstructorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ManyToOne;

public class CourseDto {

    @JsonIgnore
    private Long id;
    private String name;
    private String course_type;
    private double price;
    @JsonIgnore
    private InstructorEntity instructorEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public InstructorEntity getInstructorEntity() {
        return instructorEntity;
    }

    public void setInstructorEntity(InstructorEntity instructorEntity) {
        this.instructorEntity = instructorEntity;
    }

    public CourseEntity toEntity() {
        CourseEntity entity = new CourseEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setCourse_type(this.course_type);
        entity.setInstructorEntity(this.instructorEntity);
        entity.setPrice(this.price);
        return entity;
    }

    public void update(CourseDto courseDto) {
        this.price = courseDto.getPrice();
        this.name = courseDto.getName();
        this.course_type = courseDto.getCourse_type();
        this.instructorEntity = courseDto.getInstructorEntity();
    }
}
