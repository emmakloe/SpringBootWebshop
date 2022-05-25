package com.webshop.backend.service;

import com.webshop.backend.dto.CartWishlist.ItemAtCartDto;
import com.webshop.backend.dto.CartWishlist.CartItemDto;
import com.webshop.backend.model.AppUser;
import com.webshop.backend.model.Item;
import com.webshop.backend.model.WishlistItem;
import com.webshop.backend.repository.ItemRepository;
import com.webshop.backend.repository.WishlistItemRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@NoArgsConstructor
@Transactional
public class WishlistItemService {
    @Autowired
    private WishlistItemRepository wishlistItemRepository;
    @Autowired
    private ItemRepository itemRepository;

    public void addToWishlist(Item item, AppUser user, int quantity){
        if(wishlistItemRepository.findByItem(item) != null){
            Logger.getAnonymousLogger().log(Level.INFO,"Add quantity to already existing wishlist item");
            wishlistItemRepository.findByItem(item).addQuantity(quantity);
        }
        else {
            WishlistItem wishlistitem = new WishlistItem(item, quantity, user);
            wishlistItemRepository.save(wishlistitem);
        }
    }

    public List<CartItemDto> wishlistItemList(AppUser user){
        List<WishlistItem> wishlistItemList = wishlistItemRepository.findAllByUser(user);
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        for(int i = 0; i < wishlistItemList.size(); i++){
            cartItemDtoList.add(new CartItemDto(wishlistItemList.get(i)));
        }
        return cartItemDtoList;
    }

    public void updateWishlistItems(ItemAtCartDto updateItem, AppUser user, Item item) throws Exception {
        if(updateItem.getQuantity() == 0){
            deleteWishlistItem(updateItem.getItemID());
        }
        WishlistItem wishlistItem = wishlistItemRepository.getById(updateItem.getItemID());
        wishlistItem.setQuantity(updateItem.getQuantity());
        wishlistItemRepository.save(wishlistItem);
    }

    public void deleteWishlistItem(long itemID) throws Exception {
        if(wishlistItemRepository.getById(itemID) == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"No such item exists");
            return;
        }
        Item item = itemRepository.getById(itemID);
        wishlistItemRepository.findAllByItem(item);
        wishlistItemRepository.deleteAllByItem(item);
        //wishlistItemRepository.deleteById(itemID);
    }

    public void deleteWishlistItemsfromItemID(long itemID) throws Exception {
        if(itemRepository.findById(itemID).orElse(null) == null){
            return;
        }
        List<WishlistItem> delete = wishlistItemRepository.findAllByItem(itemRepository.findById(itemID).orElse(null));
        for(int i = 0; i < delete.size(); i++){
            wishlistItemRepository.deleteById(delete.get(i).getWishlistitemID());
        }
    }
}
