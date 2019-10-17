package com.casestudy.ecart.service;

import com.casestudy.ecart.model.Items;
import com.casestudy.ecart.model.Users;
import com.casestudy.ecart.repository.ItemsRepository;
import com.casestudy.ecart.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class CurrentUserService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ItemsRepository itemsRepository;

    public Users getUser(Principal principal) {
        Optional<Users> users = usersRepository.findByUsername(principal.getName());
        return users.get();
    }

    public Items editProduct(Items items,int id) {
        Items old = itemsRepository.findById(id).get();
        items.setId(old.getId());
        itemsRepository.saveAndFlush(items);
        return items;
    }

}
