package com.casestudy.ecart.service;

import com.casestudy.ecart.model.Cart;
import com.casestudy.ecart.model.Items;
import com.casestudy.ecart.model.Orders;
import com.casestudy.ecart.model.Users;
import com.casestudy.ecart.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    UsersRepositoryClass usersRepositoryClass;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ItemsRepositoryClass itemsRepositoryClass;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    UsersRepository usersRepository;

    private ArrayList<Cart> getCartFromCurrentuser(Principal principal) {
        Optional<Users> users = usersRepositoryClass.getByUsername(principal.getName());
        ArrayList<Cart> cart = cartRepository.findAllByUsers(users);
        return cart;
    }

    public ArrayList<Cart> getUsername(Principal principal) {
        String username = principal.getName();
        Optional<Users> users = usersRepositoryClass.getByUsername(username);
        return cartRepository.findAllByUsers(users);
    }

    public String addItemToCart(Principal principal,int id) {
        Optional<Items> items = itemsRepositoryClass.getById(id);
        Optional<Users> users = usersRepositoryClass.getByUsername(principal.getName());
        ArrayList<Cart> cart = getCartFromCurrentuser(principal);

        for(int i=0;i<cart.size();i++){
            Cart cartObject = cart.get(i);

            if(cartObject.getItems()==items.get()) {
                cartObject.setQuantity(cartObject.getQuantity()+1);

                cartRepository.save(cartObject);
                return "\"Quantity Increased\"";

            }
        }

        Cart cartObjecct = new Cart();
        cartObjecct.setQuantity(1);
        cartObjecct.setItems(items.get());
        cartObjecct.setUsers(users.get());
        cartRepository.save(cartObjecct);
        return "\"items added to cart\"";
    }

    @Transactional
    public String deleteItemFromCart(int itemid,Principal principal) {
        Optional<Items> items = itemsRepositoryClass.getById(itemid);
        Optional<Users> users = usersRepositoryClass.getByUsername(principal.getName());

        cartRepository.deleteByUsersAndItems(users,items);

        return "\"deletion Competed\"";
    }

    public String increment(int value,int itemid,Principal principal) {
        ArrayList<Cart> cart = getCartFromCurrentuser(principal);
        Optional<Items> items = itemsRepositoryClass.getById(itemid);

        for (int i=0;i<cart.size();i++){
            Cart cartObject = cart.get(i);

            if(cartObject.getItems()==items.get()){
                cartObject.setQuantity(cartObject.getQuantity()+value);
                cartRepository.save(cartObject);

                return "\"Successful\"";
            }
        }
        return "\"Unsuccessful\"";
    }

    public String decrement(int value,int itemid,Principal principal) {
        ArrayList<Cart> cart = getCartFromCurrentuser(principal);
        Optional<Items> items = itemsRepositoryClass.getById(itemid);

        for (int i=0;i<cart.size();i++){
            Cart cartObject = cart.get(i);

            if(cartObject.getItems()==items.get()){
                cartObject.setQuantity(cartObject.getQuantity()-value);
                cartRepository.save(cartObject);

                return "\"Successful\"";
            }
        }
        return "\"Unsuccessful\"";
    }

    public List<Orders> checkOut(Principal principal) {
        Optional<Users> users = usersRepositoryClass.getByUsername(principal.getName());
        ArrayList<Cart> cartList = cartRepository.findAllByUsers(users);

        for (int i=0;i<cartList.size();i++) {
            Cart cartObject = cartList.get(i);
            Orders orders = new Orders();

            orders.setUserId(cartObject.getUsers().getId());
            orders.setQuantity(cartObject.getQuantity());
            orders.setPrice(cartObject.getItems().getUnitPrice());
            orders.setItemName(cartObject.getItems().getName());
            orders.setDate(new Date());
            cartRepository.delete(cartObject);
            ordersRepository.saveAndFlush(orders);
        }
        return ordersRepository.findAllByUserId(users.get().getId());
    }
}
