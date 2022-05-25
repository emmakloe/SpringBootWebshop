package com.webshop.backend.repository;

import com.webshop.backend.model.AppUser;
import com.webshop.backend.model.CartItem;
import com.webshop.backend.model.Item;
import com.webshop.backend.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findAllByUser(AppUser user);
    List<WishlistItem> findAllByItem(Item item);
    void deleteAllByItem(Item item);
    WishlistItem findByItem(Item item);
}