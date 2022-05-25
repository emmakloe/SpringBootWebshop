package com.webshop.backend.dto.CartWishlist;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalPrice;

    public CartDto(List<CartItemDto> list){
        this.cartItems = list;
        this.totalPrice = calcTotalPrice();
    }

    private double calcTotalPrice(){
        double tmp = 0;
        for(int i =0; i < cartItems.size(); i++){
            tmp += cartItems.get(i).getQuantity() * cartItems.get(i).getItem().getPrice();
        }
        return tmp;
    }
}
