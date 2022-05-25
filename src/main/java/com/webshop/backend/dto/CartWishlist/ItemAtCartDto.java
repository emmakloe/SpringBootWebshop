package com.webshop.backend.dto.CartWishlist;

import lombok.Data;

@Data
public class ItemAtCartDto {
    private String token;
    private long itemID;
    private int quantity;
}
