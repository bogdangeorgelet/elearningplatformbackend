package com.elearningplatformservices.repository;

import com.elearningplatformservices.dto.CourseDto;
import com.elearningplatformservices.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICourseRepository extends CrudRepository<CourseEntity, Long> {
    CourseEntity findByName(String name);
}
