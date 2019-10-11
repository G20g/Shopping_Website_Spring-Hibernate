package com.casestudy.ecart.repository;

import com.casestudy.ecart.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersRepositoryClass {

    @Autowired
    UsersRepository usersRepository;

    public void add(Users user){
        System.out.println("Adding a value");
        usersRepository.save(user);
    }

    public Optional<Users> getByUsername(String username) {
        System.out.println("getting by Username");
        return usersRepository.findByUsername(username);
    }

    public Optional<Users> getById(int id) {
        return usersRepository.findById(id);
    }
}
