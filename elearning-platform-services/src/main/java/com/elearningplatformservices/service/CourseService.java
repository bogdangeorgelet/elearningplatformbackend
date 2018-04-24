package com.elearningplatformservices.service;

import com.elearningplatformservices.dto.CourseDto;
import com.elearningplatformservices.dto.CustomerDto;
import com.elearningplatformservices.entity.CourseEntity;
import com.elearningplatformservices.entity.CustomerEntity;
import com.elearningplatformservices.enums.CourseCategories;
import com.elearningplatformservices.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

    public List<CourseDto> getAllCoursesByInstructor(String firstName) {
        List<CourseEntity> allCourses = this.courseRepository
                .findAllCoursesByInstructorFirstName(firstName);
        return allCourses.stream()
                .map(CourseEntity::toDto)
                .collect(toList());
    }

    public ResponseEntity<List<CustomerEntity>> getCourseByCustomer(String username) {
        if (this.courseRepository.findCoursesByCustomerUsername(username) != null) {
            return new ResponseEntity<>(this.courseRepository.findCoursesByCustomerUsername(username)
                    .getCustomer(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public List<CourseDto> getByCategory(CourseCategories category) {
        List<CourseEntity> all = this.courseRepository.findAllByCategory(category);
        return all.stream()
                .map(CourseEntity::toDto)
                .collect(Collectors.toList());
    }

    public CourseDto getOne(Long id) {
        CourseEntity courseEntity = courseRepository.findOne(id);
        if (courseEntity != null)
            return courseEntity.toDto();
        else
            return null;
    }

    public void create(CourseDto newCourse) {
        CourseEntity courseEntity = new CourseEntity().update(newCourse);
        this.courseRepository.save(courseEntity);
    }

    public void update(Long id,
                         CourseDto courseDto) {
        CourseEntity courseEntity = courseRepository.findOne(id);
        courseEntity.setName(courseDto.getName());
        courseEntity.setPrice(courseDto.getPrice());
        courseEntity.setCourse_type(courseDto.getCourse_type());
        courseEntity.setCustomer(courseDto.getCustomer());
        courseRepository.save(courseEntity);
    }

    public void delete(Long id) {
        courseRepository.delete(id);
    }

}
