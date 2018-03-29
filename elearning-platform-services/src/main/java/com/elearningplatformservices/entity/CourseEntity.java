package com.elearningplatformservices.entity;

import com.elearningplatformservices.dto.CourseDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String course_type;
    private Double price;


    public CourseDto toDto() {
        CourseDto dto = new CourseDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setCourse_type(this.course_type);
        dto.setPrice(this.price);
        return dto;
    }

    public CourseEntity toEntity(CourseDto courseDto) {
        this.id = courseDto.getId();
        this.name = courseDto.getName();
        this.course_type = courseDto.getCourse_type();
        this.price = courseDto.getPrice();
        return this;
    }

    public CourseEntity() {
    }

}
