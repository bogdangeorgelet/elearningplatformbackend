package com.elearningplatformservices.repository;

import com.elearningplatformservices.dto.CourseDto;
import com.elearningplatformservices.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findAllCoursesByInstructorFirstName(String firstName);

    CourseEntity findCourseByCustomerUsername(String username);
}
