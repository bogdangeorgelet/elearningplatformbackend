package com.elearningplatformservices.repository;

import com.elearningplatformservices.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {
}
