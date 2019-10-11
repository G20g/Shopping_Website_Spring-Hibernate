package com.casestudy.ecart.controller;


import com.casestudy.ecart.model.Cart;
import com.casestudy.ecart.repository.CartRepository;
import com.casestudy.ecart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping(path = "/showcart")
    public ArrayList<Cart> getCart(Principal principal) {
    return cartService.getUsername(principal);
    }

    @GetMapping(path = "/addItem/{pid}")
    public String addItemToCart(@PathVariable("pid") int pid,Principal principal){
        return cartService.addItemToCart(principal,pid);
    }

    @GetMapping(path = "/deleteItem/{pid}")
    public String deleteItemFromCart(@PathVariable("pid") int pid,Principal principal){
        return cartService.deleteItemFromCart(pid,principal);
    }

    @GetMapping(path = "/increment/{value}/{pid}")
    public String increment(@PathVariable("value") int value, @PathVariable("pid") int pid,Principal principal){
        return cartService.increment(value,pid,principal);
    }

    @GetMapping(path = "/decrement/{value}/{pid}")
    public String decrement(@PathVariable("value") int value, @PathVariable("pid") int pid,Principal principal){
        return cartService.decrement(value,pid,principal);
    }
}
