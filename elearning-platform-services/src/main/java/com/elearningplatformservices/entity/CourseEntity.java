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
@Table(name = "COURSES")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "COURSE_TYPE")
    private String course_type;
    @Column(name = "PRICE")
    private Double price;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "COURSES_CUSTOMER",
            joinColumns = @JoinColumn(name = "COURSES_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID"))
    private List<CustomerEntity> customer;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INSTRUCTOR_ID")
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
