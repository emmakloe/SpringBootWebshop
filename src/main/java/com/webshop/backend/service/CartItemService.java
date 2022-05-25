package com.webshop.backend.service;

import com.webshop.backend.dto.CartWishlist.ItemAtCartDto;
import com.webshop.backend.dto.CartWishlist.CartItemDto;
import com.webshop.backend.model.AppUser;
import com.webshop.backend.model.CartItem;
import com.webshop.backend.model.Item;
import com.webshop.backend.repository.CartItemRepository;
import com.webshop.backend.repository.ItemRepository;
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
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ItemRepository itemRepository;

    public void addToCart(Item item, AppUser user, int quantity){
        if(cartItemRepository.findByItem(item) != null){
            Logger.getAnonymousLogger().log(Level.INFO,"Add quantity to already existing cart item");
            cartItemRepository.findByItem(item).addQuantity(quantity);
        }
        else {
            CartItem cartitem = new CartItem(item, quantity, user);
            cartItemRepository.save(cartitem);
        }
    }

    public List<CartItemDto> cartItemList(AppUser user){
        List<CartItem> cartItemList = cartItemRepository.findAllByUser(user);
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        for(int i = 0; i < cartItemList.size(); i++){
            cartItemDtoList.add(new CartItemDto(cartItemList.get(i)));
        }
        return cartItemDtoList;
    }

    public void updateCartItems(ItemAtCartDto updateItem, AppUser user, Item item) throws Exception {
        if(updateItem.getQuantity() == 0){
            deleteCartItem(updateItem.getItemID());
        }
        CartItem cartitem = cartItemRepository.getById(updateItem.getItemID());
        cartitem.setQuantity(updateItem.getQuantity());
        cartItemRepository.save(cartitem);
    }

    public void deleteCartItem(long itemID) throws Exception {
        if(cartItemRepository.getById(itemID) == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"No such item exists");
            return;
        }
        Item item = itemRepository.getById(itemID);
        cartItemRepository.findAllByItem(item);
        cartItemRepository.deleteAllByItem(item);
    }

    public void deleteCartItemsfromItemID(long itemID) {
        if(itemRepository.findById(itemID).orElse(null) == null){
            return;
        }
        List<CartItem> delete = cartItemRepository.findAllByItem(itemRepository.findById(itemID).orElse(null));
        for(int i = 0; i < delete.size(); i++){
            cartItemRepository.deleteById(delete.get(i).getCartitemID());
        }
    }

    public void deleteCartItemsFromUser(AppUser user){
        cartItemRepository.deleteAllByUser(user);
    }
}
