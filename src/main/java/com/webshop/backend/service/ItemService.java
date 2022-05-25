package com.webshop.backend.service;

import com.webshop.backend.dto.ItemDto;
import com.webshop.backend.model.Item;
import com.webshop.backend.repository.CartItemRepository;
import com.webshop.backend.repository.ItemRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@NoArgsConstructor
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private WishlistItemService wishlistItemService;

    public Item getITemByID(Long itemid){ return itemRepository.findById(itemid).orElse(null);}


    public List<Item> getAllITems(){return itemRepository.findAll();}

    public Item addItem(ItemDto itemdto){
        Item item = new Item();
        item.setName(itemdto.getName());
        item.setImageURL(itemdto.getImageURL());
        item.setPrice(itemdto.getPrice());
        return itemRepository.save(item);
    }

    public void update(long itemID, ItemDto itemDto) throws Exception {
        Item item = getITemByID(itemID);
        if(item == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"Item not found");
            throw new Exception("Item not found");
        }
        item.setItemID(itemID);
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setImageURL(itemDto.getImageURL());
        itemRepository.save(item);
    }

    public void updatePrice(long itemID,double price){
        getITemByID(itemID).setPrice(price);
    }

    public void delete(long itemID) throws Exception {
        cartItemService.deleteCartItemsfromItemID(itemID);
        wishlistItemService.deleteWishlistItemsfromItemID(itemID);
        itemRepository.deleteById(itemID);
    }
}
