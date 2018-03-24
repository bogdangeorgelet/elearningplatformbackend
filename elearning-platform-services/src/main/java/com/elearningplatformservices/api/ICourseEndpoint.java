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

    @GetMapping(path = "/name")
    ResponseEntity<CourseDto> getOne(String name);

    @DeleteMapping(path = "/{name}")
    String delete(String name);

    @PostMapping(path = "/add")
    String create(CourseDto newCourse);

    @PutMapping(path = "/{serialNumber}")
    String update(String serialNumber, CourseDto courseDto);
}
