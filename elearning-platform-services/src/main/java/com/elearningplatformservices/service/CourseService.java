package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.CourseDto;
import com.elearningplatformservices.entity.CourseEntity;
import com.elearningplatformservices.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final ICourseRepository courseRepository;

    @Autowired
    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDto> getAllCourses() {
        List<CourseDto> allCourses = new ArrayList<>();
        courseRepository.findAll().forEach(courseEntity -> {
            allCourses.add(courseEntity.toDto());
        });
        return allCourses;
    }

    public CourseDto getOne(Long id) {
        CourseEntity courseEntity = courseRepository.findOne(id);
        if (courseEntity != null)
            return courseEntity.toDto();
        else
            return null;
    }

    public void create(CourseDto newCourse) {
        CourseEntity courseEntity = new CourseEntity().toEntity(newCourse);
        this.courseRepository.save(courseEntity);
    }

    public void update(Long id,
                         CourseDto courseDto) {
        CourseEntity courseEntity = courseRepository.findOne(id);
        courseEntity.setName(courseDto.getName());
        courseEntity.setPrice(courseDto.getPrice());
        courseEntity.setCourse_type(courseDto.getCourse_type());
        courseRepository.save(courseEntity);
    }

    public void delete(Long id) {
        courseRepository.delete(id);
    }

}
