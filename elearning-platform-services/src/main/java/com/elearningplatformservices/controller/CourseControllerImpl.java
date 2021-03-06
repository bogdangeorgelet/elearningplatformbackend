package com.elearningplatformservices.controller;

import com.elearningplatformservices.api.ICourseEndpoint;
import com.elearningplatformservices.dto.CourseDto;
import com.elearningplatformservices.dto.CustomerDto;
import com.elearningplatformservices.entity.CustomerEntity;
import com.elearningplatformservices.enums.CourseCategories;
import com.elearningplatformservices.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@CrossOrigin
public class CourseControllerImpl implements ICourseEndpoint {

    private CourseService courseService;

    @Autowired
    public CourseControllerImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<CourseDto> getAll() {
        return courseService.getAllCourses();
    }

    // TODO implement courseByCustomerUsername

    public List<CourseDto> getAllCoursesByInstructorFirstName(@PathVariable String firstName) {
        return courseService.getAllCoursesByInstructor(firstName);
    }

    public List<CourseDto> getCoursesByCustomerUsername(@PathVariable String username) {
        return courseService.getCourseByCustomer(username);
    }

    public List<CourseDto> getAllByCategory(@PathVariable CourseCategories category) {
        return courseService.getByCategory(category);
    }

    public ResponseEntity<CourseDto> getOne(@PathVariable Long id) {
        CourseDto courseDto = courseService.getOne(id);
        if (courseDto != null)
            return new ResponseEntity<>(courseDto, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
