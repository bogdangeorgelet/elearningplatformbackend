package com.elearningplatformservices.entity;

import com.elearningplatformservices.dto.CourseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String course_type;
    private Double price;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "course_customer",
            joinColumns = @JoinColumn(name = "courses_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"))
    private List<CustomerEntity> customer;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="instructor_id")
    private InstructorEntity instructor;

    public CourseEntity() {
    }

    public CourseDto toDto() {
        CourseDto dto = new CourseDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setCourse_type(this.course_type);
        dto.setPrice(this.price);
        dto.setInstructor(this.instructor);
        dto.setCustomer(this.customer);
        return dto;
    }

    public CourseEntity update(CourseDto courseDto) {
        this.id = courseDto.getId();
        this.name = courseDto.getName();
        this.course_type = courseDto.getCourse_type();
        this.price = courseDto.getPrice();
        this.instructor=courseDto.getInstructor();
        this.customer=courseDto.getCustomer();
        return this;
    }
}
