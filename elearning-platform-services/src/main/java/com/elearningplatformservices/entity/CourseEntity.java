package com.elearningplatformservices.entity;

import com.elearningplatformservices.dto.CourseDto;

import javax.persistence.*;

@Entity
public class CourseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String course_type;
    private double price;
    @ManyToOne
    private InstructorEntity instructorEntity;

    public CourseDto toDto() {
        CourseDto dto = new CourseDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setCourse_type(this.course_type);
        dto.setPrice(this.price);
        dto.setInstructorEntity(this.instructorEntity);
        return dto;
    }

    public CourseEntity(String name, String course_type, double price, InstructorEntity instructorEntity) {
        this.name = name;
        this.course_type = course_type;
        this.price = price;
        this.instructorEntity = instructorEntity;
    }

    public CourseEntity() {
    }

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
}
