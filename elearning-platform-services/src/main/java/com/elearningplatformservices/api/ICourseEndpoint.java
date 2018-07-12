package com.elearningplatformservices.api;

import com.elearningplatformservices.dto.CourseDto;
import com.elearningplatformservices.dto.CustomerDto;
import com.elearningplatformservices.entity.CustomerEntity;
import com.elearningplatformservices.enums.CourseCategories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/course")
public interface ICourseEndpoint {

    @GetMapping
    List<CourseDto> getAll();

    @GetMapping("/allBy/{firstName}")
    List<CourseDto> getAllCoursesByInstructorFirstName(@PathVariable String firstName);

    @GetMapping("/byCustomer/{username}")
    List<CourseDto> getCoursesByCustomerUsername(@PathVariable String username);

    @GetMapping("/getAllByCategory/{category}")
    List<CourseDto> getAllByCategory(@PathVariable CourseCategories category);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<CourseDto> getOne(@PathVariable Long id);

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody CourseDto newCourse);

    @PutMapping("/{id}")
    void update(@PathVariable Long id, @RequestBody CourseDto courseDto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
