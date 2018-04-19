package com.elearningplatformservices.entity;


import com.elearningplatformservices.dto.InstructorDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "INSTRUCTORS")
public class InstructorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @OneToMany(mappedBy = "instructor")
    private List<CourseEntity> courses;

    public InstructorEntity() {
    }

    public InstructorDto toDto() {
        InstructorDto instructorDto = new InstructorDto();
        if (this.id != null)
            instructorDto.setId(this.id);
        instructorDto.setFirstName(this.firstName);
        instructorDto.setLastName(this.lastName);
        instructorDto.setPassword(this.password);
        instructorDto.setEmail(this.email);
        instructorDto.setDateCreated(this.dateCreated);
        instructorDto.setCourses(this.courses);
        return instructorDto;
    }

    public InstructorEntity update(InstructorDto instructorDto) {
        if (instructorDto.getId() != null)
            this.id = instructorDto.getId();
        this.firstName = instructorDto.getFirstName();
        this.lastName = instructorDto.getLastName();
        this.email = instructorDto.getEmail();
        this.password = instructorDto.getPassword();
        this.dateCreated = instructorDto.getDateCreated();
        this.courses = instructorDto.getCourses();
        return this;
    }
}
