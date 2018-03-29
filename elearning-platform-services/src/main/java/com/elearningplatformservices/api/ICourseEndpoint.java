package com.elearningplatformservices.api;

import com.elearningplatformservices.dto.CourseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/course")
public interface ICourseEndpoint {

    @GetMapping
    List<CourseDto> getAll();

    @GetMapping("/{id}")
    CourseDto getOne(@PathVariable Long id);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

    @PostMapping("/add")
    void create(@RequestBody CourseDto newCourse);

    @PutMapping("/{id}")
    void update(@PathVariable Long id, @RequestBody CourseDto courseDto);
}
