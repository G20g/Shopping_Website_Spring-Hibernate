package com.casestudy.ecart.controller;

import com.casestudy.ecart.model.Users;
import com.casestudy.ecart.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.casestudy.ecart.exception.ResourceNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/addUsers")
    public Users addUsers(@Valid @RequestBody Users users) {
        users.setActive(1);
        users.setAuthorize("Customer");
        usersRepository.save(users);
        return users;
    }

    @GetMapping("/getUsers")
    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/validate")
    public String valUser()
    {
        return "\"You Are Valid Authenticated User\"";
    }



}
