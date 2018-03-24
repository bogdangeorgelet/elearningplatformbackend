package com.elearningplatformservices.controllers;

import com.elearningplatformservices.api.ICourseEndpoint;
import com.elearningplatformservices.dto.CourseDto;
import com.elearningplatformservices.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Component
public class CourseControllerImpl implements ICourseEndpoint{

    private CourseService courseService;

    @Autowired
    public CourseControllerImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<CourseDto> getAll() {
        return courseService.getAllCourses();
    }

    public ResponseEntity<CourseDto> getOne(String name) {
        return courseService.getOne(name);
    }

    public String delete(String name) {
        return courseService.delete(name);
    }

    public String create(CourseDto newCourse) {
        return courseService.create(newCourse);
    }

    public String update(String serialNumber, CourseDto courseDto) {
        return courseService.update(serialNumber, courseDto);
    }
}
