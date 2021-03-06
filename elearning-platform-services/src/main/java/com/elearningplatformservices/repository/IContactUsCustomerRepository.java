package com.elearningplatformservices.repository;

import com.elearningplatformservices.entity.ContactUsCustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface IContactUsCustomerRepository extends CrudRepository<ContactUsCustomerEntity, Long> {
    ContactUsCustomerEntity findByFirstName(String firstName);
    ContactUsCustomerEntity findByEmail(String email);
}
