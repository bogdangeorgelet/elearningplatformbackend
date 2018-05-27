package com.elearningplatformservices.controller;

import com.elearningplatformservices.entity.CustomerEntity;
import com.elearningplatformservices.repository.ICustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
public class SimpleWebController implements Serializable {

  @Autowired
  ICustomerRepository customerRepository;

  Logger log = LoggerFactory.getLogger(this.getClass());

  @GetMapping(value = "/form")
  public String customerForm(Model model) {
    model.addAttribute("customer", new CustomerEntity());
    return "form";
  }

  @PostMapping(value = "/form")
  public String customerSubmit(@ModelAttribute CustomerEntity customer, Model model) {
    model.addAttribute("customer", customer);

    String info = String.format("Customer Submission: id = %d, fullName = %s",
      customer.getId(), customer.getFullName());

    log.info(info);
    customerRepository.save(customer);

    return "result";
  }

  @GetMapping(value = "/load")
  public String customerSubmit(@RequestParam("id") long id, Model model) {
    CustomerEntity customer = customerRepository.findOne(id);
    model.addAttribute("customer", customer);

    return "load";
  }

}
