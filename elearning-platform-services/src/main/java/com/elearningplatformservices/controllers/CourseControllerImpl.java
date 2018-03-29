package com.elearningplatformservices.controllers;

import com.elearningplatformservices.api.ICourseEndpoint;
import com.elearningplatformservices.dto.CourseDto;
import com.elearningplatformservices.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@CrossOrigin
public class CourseControllerImpl implements ICourseEndpoint{

    private CourseService courseService;

    @Autowired
    public CourseControllerImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<CourseDto> getAll() {
        return courseService.getAllCourses();
    }

    public CourseDto getOne(@PathVariable Long id) {
        return courseService.getOne(id);
    }

    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }

    public void create(@RequestBody CourseDto newCourse) {
        courseService.create(newCourse);
    }

    public void update(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        courseService.update(id, courseDto);
    }
}
