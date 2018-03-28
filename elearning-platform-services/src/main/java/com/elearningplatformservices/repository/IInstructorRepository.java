package com.elearningplatformservices.repository;

import com.elearningplatformservices.entity.InstructorEntity;
import org.springframework.data.repository.CrudRepository;


public interface IInstructorRepository extends CrudRepository<InstructorEntity, Long> {
}
