package com.casestudy.ecart.service;

import com.casestudy.ecart.model.Users;
import com.casestudy.ecart.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class CurrentUserService {
    @Autowired
    UsersRepository usersRepository;

    public Users getUser(Principal principal) {
        Optional<Users> users = usersRepository.findByUsername(principal.getName());
        return users.get();
    }
}
