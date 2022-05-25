package com.webshop.backend.controller;

import com.webshop.backend.dto.CartWishlist.ItemAtCartDto;
import com.webshop.backend.dto.CartWishlist.CartDto;
import com.webshop.backend.dto.CartWishlist.GetListDto;
import com.webshop.backend.model.AppUser;
import com.webshop.backend.model.Item;
import com.webshop.backend.service.AuthTokenService;
import com.webshop.backend.service.CartItemService;
import com.webshop.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
@RequestMapping("/api/cart")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private AuthTokenService authTokenService;

    @PostMapping("/add")
    public String addToCart(@RequestBody ItemAtCartDto itemAtCartDto) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"Add item to cart");
        AppUser user = authTokenService.getUser(itemAtCartDto.getToken());
        if(user == null){
            throw new Exception("Cannot find user");
        }
        Item item = itemService.getITemByID(itemAtCartDto.getItemID());
        cartItemService.addToCart(item,user, itemAtCartDto.getQuantity());
        return "Added to cart";
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<CartDto> getCart(@RequestBody GetListDto getlist) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"Get cart");
        AppUser user = authTokenService.getUser(getlist.getToken());
        if(user == null){
            throw new Exception("Cannot find user");
        }
        CartDto cartDto = new CartDto(cartItemService.cartItemList(user));
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public String updateCartItems(@RequestBody ItemAtCartDto itemAtCartDto) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"Update cart item");
        AppUser user = authTokenService.getUser(itemAtCartDto.getToken());
        if(user == null){
            throw new Exception("Cannot find user");
        }
        Item item = itemService.getITemByID(itemAtCartDto.getItemID());
        cartItemService.updateCartItems(itemAtCartDto,user,item);
        return "Updated cart";
    }

    @DeleteMapping("/delete/{itemID}/{token}")
    public String deleteCartItem(@PathVariable("itemID") long itemID,@PathVariable("token") String token) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"Delete item from cart");
        AppUser user = authTokenService.getUser(token);
        if(user == null){
            throw new Exception("Cannot find user");
        }
        cartItemService.deleteCartItem(itemID);
        return "Item deleted from cart";
    }

}
