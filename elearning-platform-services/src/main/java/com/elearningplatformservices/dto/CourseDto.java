package com.elearningplatformservices.dto;

import com.elearningplatformservices.entity.CourseEntity;
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
    private double price;
    @JsonIgnore
    private InstructorEntity instructorEntity;

    public CourseDto(String name, String course_type, double price, InstructorEntity instructorEntity) {
        this.name = name;
        this.course_type = course_type;
        this.price = price;
        this.instructorEntity = instructorEntity;
    }

    public CourseDto() {
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
