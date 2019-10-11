package com.casestudy.ecart.repository;

import com.casestudy.ecart.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class ItemsRepositoryClass {

    @Autowired
    ItemsRepository itemsRepository;

    public boolean addProduct(Items items){
        try{
            System.out.println("Adding a Product");
            itemsRepository.save(items);
            System.out.println("Item Added");
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public ArrayList<Items> getAllProducts() {
        return (ArrayList<Items>) itemsRepository.findAll();
    }

    public Optional<Items> getFeilds(int id) {
        return itemsRepository.findById(id);
    }

    public ArrayList<Items> getByCategory(String category) {
        return (ArrayList<Items>)itemsRepository.findAllByCategory(category);
    }

    public ArrayList<Items> getBycategoryAndPrice(String cat,double c1,double c2){
        return (ArrayList<Items>)itemsRepository.findAllByCategoryAndUnitPriceBetween(cat,c1,c2);

    }

    public Optional<Items> getById(int id) {
        return itemsRepository.findById(id);
    }
}
