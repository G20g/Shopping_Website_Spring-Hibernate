package com.casestudy.ecart.controller;

import com.casestudy.ecart.model.Items;
import com.casestudy.ecart.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.casestudy.ecart.exception.ResourceNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class ItemsController {

    @Autowired
    ItemsRepository itemsRepository;

    @GetMapping("/items")
    public List<Items> getAllItems() {
        return itemsRepository.findAll();
    }

    @PostMapping("/additems")
        public Items addproduct(@Valid @RequestBody Items items) {
            return itemsRepository.save(items);
        }

    @GetMapping("/items/{id}")
    public Items getProductById(@PathVariable(value = "id") Integer productId) {
        return itemsRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }
    @GetMapping("/category/{type}")
    public List<Items> getProductByCategory(@PathVariable(value = "type") String productCategory) {
        return itemsRepository.findAllByCategory(productCategory);
    }

    @GetMapping("/{cat}/{c1}/{c2}")
    public List<Items> getCategoryWithPrice(@PathVariable(value = "cat") String cat, @PathVariable(value = "c1") Double c1, @PathVariable(value = "c2") Double c2){
        return itemsRepository.findAllByCategoryAndUnitPriceBetween(cat,c1,c2);
    }

    @GetMapping("/{c1}/{c2}")
    public  List<Items> getWithPrice(@PathVariable(value = "c1") Double c1, @PathVariable(value = "c2") Double c2) {
        return itemsRepository.findAllByUnitPriceBetween(c1, c2);
    }

    @GetMapping("/{b}")
    public List<Items> getWithBrand(@PathVariable(value = "b") String b) {
        return itemsRepository.findAllByBrandContaining(b);
    }

    @GetMapping("/{cat}/{b}")
    public List<Items> getCategoryWithBrand(@PathVariable(value = "cat") String cat, @PathVariable(value = "b") String b) {
        return itemsRepository.findAllByCategoryAndBrandContaining(cat, b);
    }
}
