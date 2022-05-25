package com.webshop.backend.controller;

import com.webshop.backend.dto.CartWishlist.ItemAtCartDto;
import com.webshop.backend.dto.CartWishlist.CartDto;
import com.webshop.backend.dto.CartWishlist.GetListDto;
import com.webshop.backend.model.AppUser;
import com.webshop.backend.model.Item;
import com.webshop.backend.service.AuthTokenService;
import com.webshop.backend.service.CartItemService;
import com.webshop.backend.service.ItemService;
import com.webshop.backend.service.WishlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
@RequestMapping("/api/wishlist")
public class WihslistItemController {
    @Autowired
    private WishlistItemService wishlistItemService;
    @Autowired
    private AuthTokenService authTokenService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CartItemService cartItemService;


    @PostMapping("/add")
    public String addToWishlist(@RequestBody ItemAtCartDto itemAtCartDto) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"Add item to wishlist");
        AppUser user = authTokenService.getUser(itemAtCartDto.getToken());
        if(user == null){
            throw new Exception("Cannot find user");
        }
        Item item = itemService.getITemByID(itemAtCartDto.getItemID());
        wishlistItemService.addToWishlist(item,user, itemAtCartDto.getQuantity());
        return "Added to wishlist";
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<CartDto> getWishlist(@RequestBody GetListDto getlist) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"Get wishlist");
        AppUser user = authTokenService.getUser(getlist.getToken());
        if(user == null){
            throw new Exception("Cannot find user");
        }
        CartDto cartDto = new CartDto(wishlistItemService.wishlistItemList(user));
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public String updateWishlistItems(@RequestBody ItemAtCartDto itemAtCartDto) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"Update wishlist item");
        AppUser user = authTokenService.getUser(itemAtCartDto.getToken());
        if(user == null){
            throw new Exception("Cannot find user");
        }
        Item item = itemService.getITemByID(itemAtCartDto.getItemID());
        wishlistItemService.updateWishlistItems(itemAtCartDto,user,item);
        return "Updated wishlist";
    }

    @DeleteMapping("/delete/{itemID}/{token}")
    public String deleteWishlistItem(@PathVariable("itemID") long itemID, @PathVariable("token") String token) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"Delete item from wishlist");
        AppUser user = authTokenService.getUser(token);
        if(user == null){
            throw new Exception("Cannot find user");
        }
        wishlistItemService.deleteWishlistItem(itemID);
        return "Item deleted from wishlist";
    }

}
