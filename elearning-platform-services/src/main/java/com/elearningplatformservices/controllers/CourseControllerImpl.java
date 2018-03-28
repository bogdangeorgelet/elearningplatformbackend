package com.elearningplatformservices.controllers;

import com.elearningplatformservices.api.ICourseEndpoint;
import com.elearningplatformservices.dto.CourseDto;
import com.elearningplatformservices.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Component
public class CourseControllerImpl implements ICourseEndpoint{

    private CourseService courseService;

    @Autowired
    public CourseControllerImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDto> getAll() {
        return courseService.getAllCourses();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseDto> getOne(@PathVariable Long id) {
        return courseService.getOne(id);
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable Long id) {
        return courseService.delete(id);
    }

    @PostMapping(path = "/add")
    public String create(@RequestBody CourseDto newCourse) {
        return courseService.create(newCourse);
    }

    @PutMapping(path = "/{id}")
    public String update(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        return courseService.update(id, courseDto);
    }
}
