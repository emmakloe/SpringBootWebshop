package com.webshop.backend.service;

import com.webshop.backend.dto.Order.UserOrderDto;
import com.webshop.backend.model.UserOrder;
import com.webshop.backend.repository.UserOrderRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@NoArgsConstructor
@Transactional
public class UserOrderService {
    @Autowired
    private UserOrderRepository userOrderRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private AuthTokenService authTokenService;

    public void addOrderToUser(String token, UserOrderDto userOrderDto){
        cartItemService.deleteCartItemsFromUser(authTokenService.getUser(token));
        UserOrder userOrder = new UserOrder(userOrderDto);
        userOrder.setUser(authTokenService.getUser(token));
        userOrderRepository.save(userOrder);
    }

    public List<UserOrder> getOrdersByUser(String token){
        return authTokenService.getUser(token).getUserOrders();
    }
}
