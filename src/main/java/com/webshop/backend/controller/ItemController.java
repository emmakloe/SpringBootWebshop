package com.webshop.backend.controller;

import com.webshop.backend.dto.ItemDto;
import com.webshop.backend.model.Item;
import com.webshop.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    private ItemService itemService;


    @GetMapping("/{itemID}")
    @ResponseBody
    public ResponseEntity<ItemDto> getItem(@PathVariable long itemID){
        ItemDto item = new ItemDto(itemService.getITemByID(itemID));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }


    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<ItemDto>> getItemList(){
        Logger.getAnonymousLogger().log(Level.INFO,"Get items");
        List<ItemDto> dtoList = new ArrayList<>();
        for(int i=0; i < itemService.getAllITems().size(); i++){
            dtoList.add(new ItemDto(itemService.getAllITems().get(i)));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody ItemDto itemDto){
        Logger.getAnonymousLogger().log(Level.INFO,"Add new item");
        return new ResponseEntity<>(itemService.addItem(itemDto),HttpStatus.OK);
    }

    /*@PostMapping("/update/{itemID}")
    public String updateItem(@PathVariable("itemID") long itemID, @RequestBody ItemDto itemDto) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"Update item");
        itemService.update(itemID, itemDto);
        return "Updated item";
    }*/

    @PutMapping("/update/{itemID}")
    public String updateItem(@PathVariable("itemID") long itemID, @RequestBody double price) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"Update item");
        itemService.updatePrice(itemID,price);
        return "Updated item";
    }

    @DeleteMapping("/delete/{itemID}")
    public String deleteITem(@PathVariable("itemID") long itemID) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"Delete item");
        itemService.delete(itemID);
        return "Item deleted";
    }
}
