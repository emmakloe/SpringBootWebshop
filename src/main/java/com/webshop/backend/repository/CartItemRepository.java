package com.webshop.backend.repository;

import com.webshop.backend.model.AppUser;
import com.webshop.backend.model.CartItem;
import com.webshop.backend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByUser(AppUser user);
    List<CartItem> findAllByItem(Item item);
    void deleteAllByItem(Item item);
    CartItem findByItem(Item item);
    void deleteAllByUser(AppUser appUser);
}

