package com.casestudy.ecart.repository;

import com.casestudy.ecart.model.Cart;
import com.casestudy.ecart.model.Items;
import com.casestudy.ecart.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    ArrayList<Cart> findAllByUsers(Optional<Users> users);
    void deleteByUsersAndItems(Optional<Users> user, Optional<Items> items);

}
