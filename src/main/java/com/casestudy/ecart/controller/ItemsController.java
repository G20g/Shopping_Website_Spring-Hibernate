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
    @GetMapping("/items/{category}")
    public Items getProductByCategory(@PathVariable(value = "category") String productCategory) {
        return itemsRepository.findAllByCategory(productCategory)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productCategory));
    }
}
