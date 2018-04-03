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
@Table(name = "instructors")
public class InstructorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Date dateCreated;

    @OneToMany(mappedBy = "instructor")
    private List<CourseEntity> courses;

    public InstructorEntity(String jack, String bauer, String abcd1234, String s, String s1) {
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
        return this;
    }
}
