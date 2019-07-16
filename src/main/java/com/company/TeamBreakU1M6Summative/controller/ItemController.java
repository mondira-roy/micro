package com.company.TeamBreakU1M6Summative.controller;

import com.company.TeamBreakU1M6Summative.dao.ItemDao;
import com.company.TeamBreakU1M6Summative.model.Item;
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
     * Only Item obj
     */

    @Autowired
    ItemDao itemDao;

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Item createItem(@Valid @RequestBody Item item) {
        return itemDao.addItem(item);
    }

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItemById(@PathVariable int id) {

        if(id<1){
            throw new IllegalArgumentException("Item Id must be greater than 0.");
        }

        return itemDao.getItem(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateItem(@PathVariable int id, @Valid @RequestBody Item item) {
        if (id < 1) {
            throw new IllegalArgumentException("Item Id must be greater than 0.");
        }

        itemDao.updateItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id){
        if (id < 1) {
            throw new IllegalArgumentException("Item Id must be greater than 0.");
        }

        itemDao.deleteItem(id);
    }
}
