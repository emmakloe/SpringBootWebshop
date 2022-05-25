package com.webshop.backend.dto;

import com.webshop.backend.model.Item;
import lombok.Data;

@Data
public class ItemDto {
    private long itemID;
    private String name;
    private String imageURL;
    private double price;
    private int quantity;

    public ItemDto(Item item){
        this.imageURL = item.getImageURL();
        this.itemID = item.getItemID();
        //this.quantity = item.getQuantity();
        this.price = item.getPrice();
        this.name = item.getName();
    }

    public ItemDto(){}
}
