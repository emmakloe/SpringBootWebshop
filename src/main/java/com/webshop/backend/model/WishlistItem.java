package com.webshop.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "wishlistitem")
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wishlistitemID;

    @ManyToOne
    @JoinColumn(name = "itemID", referencedColumnName = "itemID")
    private Item item;

    @JsonIgnore
    @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userID")
    private AppUser user;


    private int quantity;

    public WishlistItem(Item item, int quantity, AppUser user){
        this.user = user;
        this.item = item;
        this.quantity = quantity;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}
