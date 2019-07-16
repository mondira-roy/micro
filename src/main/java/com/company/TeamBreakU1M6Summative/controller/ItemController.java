package com.company.TeamBreakU1M6Summative.controller;

import com.company.TeamBreakU1M6Summative.model.Item;
import com.company.TeamBreakU1M6Summative.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    /**
     * This controller deals with item routes
     */

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Item createItem(@Valid @RequestBody Item customer) {
        return serviceLayer.saveItem(customer);
    }

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Item> getAllItems() {
        return serviceLayer.findAllItems();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItemById(@PathVariable int id) {

        if(id<1){
            throw new IllegalArgumentException("Item Id must be greater than 0.");
        }

        return serviceLayer.findItem(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateItem(@PathVariable int id, @Valid @RequestBody Item customer) {
        if (id < 1) {
            throw new IllegalArgumentException("Item Id must be greater than 0.");
        }

        serviceLayer.updateItem(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id){
        if (id < 1) {
            throw new IllegalArgumentException("Item Id must be greater than 0.");
        }

        serviceLayer.removeItem(id);
    }
}
