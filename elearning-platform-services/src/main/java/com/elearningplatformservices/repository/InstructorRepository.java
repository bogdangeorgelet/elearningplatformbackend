package com.elearningplatformservices.repository;

import com.elearningplatformservices.entity.InstructorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends CrudRepository<InstructorEntity, Long>{
    List<InstructorEntity> findByLastName(String lastname);
}
