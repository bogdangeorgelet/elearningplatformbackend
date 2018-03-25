package com.elearningplatformservices.repository;

import com.elearningplatformservices.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;


public interface ICustomerRepository extends CrudRepository<CustomerEntity, Long> {
    CustomerEntity findByUsername(String username);
}
