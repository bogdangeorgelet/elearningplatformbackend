package com.elearningplatformservices.entity;

import com.elearningplatformservices.dto.CourseDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

}
