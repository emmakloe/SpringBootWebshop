package com.webshop.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "item")
public class Item {

    //miert van szukseg a kulon orderitemre? nem lehetne atmasolni a cart tartalmat egy orderbe?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemID;

    private String name;

    private String imageURL;

    @PositiveOrZero
    @Min(value=0, message = "Price should be bigger than 0")
    private double price;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<WishlistItem> wishListList;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<CartItem> carts;


    public Item(String name, double price){
        this.name = name;
        this.price = price;
    }

}

