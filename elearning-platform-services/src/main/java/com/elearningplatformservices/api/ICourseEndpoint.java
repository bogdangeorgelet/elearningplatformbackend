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

    @GetMapping(path = "/{id}")
    ResponseEntity<CourseDto> getOne(Long id);

    @DeleteMapping(path = "/{id}")
    String delete(Long id);

    @PostMapping(path = "/add")
    String create(CourseDto newCourse);

    @PutMapping(path = "/{id}")
    String update(Long id, CourseDto courseDto);
}
