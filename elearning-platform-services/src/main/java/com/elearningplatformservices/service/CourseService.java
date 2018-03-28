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
        List<CourseEntity> all = (List<CourseEntity>) this.courseRepository.findAll();
        return all.stream()
                .map(CourseEntity::toDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<CourseDto> getOne(Long id) {
        if (this.courseRepository.findOne(id) != null)
            return new ResponseEntity<>(this.courseRepository.findOne(id).toDto(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public String create(CourseDto temp) {
        if (temp != null &&
                this.courseRepository.findOne(temp.getId()) != null) {
            this.courseRepository.save(temp.toEntity());
            return "Created";
        } else
            return "Couldn't create";
    }

    public String delete(Long id) {
        CourseEntity entity = this.courseRepository.findOne(id);
        if (entity != null) {
            this.courseRepository.delete(entity);
            return "Deleted";
        } else
            return "Couldn't Delete";
    }

    public String update(Long id, CourseDto courseDto) {
        if (this.courseRepository.findOne(id) != null) {
            CourseDto dto = this.courseRepository.findOne(id).toDto();
            if (courseDto.getName().equals(dto.getName())) {
                dto.update(courseDto);
                this.courseRepository.save(dto.toEntity());
                return "Updated";
            } else {
                List<CourseEntity> all = (List<CourseEntity>) this.courseRepository.findAll();
                if (all.stream().anyMatch(un -> un.getName().equals(dto.getName())))
                    return "Couldn't update";
                else {
                    dto.update(courseDto);
                    this.courseRepository.save(dto.toEntity());
                    return "Updated";
                }
            }
        } else
            return "Couldn't update";
    }

}
