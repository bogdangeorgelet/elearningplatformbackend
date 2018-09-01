package com.elearningplatformservices.repository;

import com.elearningplatformservices.dto.RegisterUserDto;
import com.elearningplatformservices.entity.RegisterUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IRegisterUserRepository extends CrudRepository<RegisterUserEntity, Long> {
}
