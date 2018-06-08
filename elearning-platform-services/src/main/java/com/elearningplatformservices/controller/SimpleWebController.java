package com.elearningplatformservices.controller;

import com.elearningplatformservices.entity.UserEntity;
import com.elearningplatformservices.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@CrossOrigin
public class SimpleWebController implements Serializable {

  @Autowired
  IUserRepository userRepository;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @GetMapping(value = "/form")
  public String customerForm(Model model) {
    model.addAttribute("customer", new UserEntity());
    return "form";
  }

  @PostMapping(value = "/form/add")
  public String customerSubmit(@ModelAttribute UserEntity user, Model model) {
    model.addAttribute("user", user);

    String info = String.format("User Submission: id = %d, firstname = %s, lastname = %s, email=%s",
            user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());

    log.info(info);
    userRepository.save(user);

    return "result";
  }

  @GetMapping(value = "/load")
  public String customerSubmit(@RequestParam("id") long id, Model model) {
    UserEntity user = userRepository.findOne(id);
    model.addAttribute("user", user);

    return "load";
  }

}
