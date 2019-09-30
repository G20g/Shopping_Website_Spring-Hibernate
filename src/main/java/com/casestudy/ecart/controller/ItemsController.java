package com.casestudy.ecart.controller;

import com.casestudy.ecart.model.Items;
import com.casestudy.ecart.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.casestudy.ecart.exception.ResourceNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ItemsController {

    @Autowired
    ItemsRepository itemsRepository;

    @GetMapping("/items")
    public List<Items> getAllNotes() {
        return itemsRepository.findAll();
    }
}
