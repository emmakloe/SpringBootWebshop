package com.webshop.backend.dto.CartWishlist;

import com.webshop.backend.model.CartItem;
import com.webshop.backend.model.Item;
import com.webshop.backend.model.WishlistItem;
import lombok.Data;

@Data
public class CartItemDto {
    private int quantity;
    private Item item;

    public CartItemDto(CartItem cartitem){
        this.quantity = cartitem.getQuantity();
        this.item = cartitem.getItem();
    }

    public CartItemDto(WishlistItem wishlistitem){
        this.quantity = wishlistitem.getQuantity();
        this.item = wishlistitem.getItem();
    }
}
