package com.webshop.backend.controller;

import com.webshop.backend.dto.Order.UserOrderDto;
import com.webshop.backend.model.UserOrder;
import com.webshop.backend.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
@RequestMapping("/api/order")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;

    @GetMapping("/{token}")
    public ResponseEntity<List<UserOrder>> getOrders(@PathVariable("token") String token){
        Logger.getAnonymousLogger().log(Level.INFO,"Get orders");
        return new ResponseEntity<>(userOrderService.getOrdersByUser(token), HttpStatus.OK);
    }

    @PostMapping("/add/{token}")
    public String addOrder(@PathVariable("token") String token, @RequestBody UserOrderDto userOrderDto){
        Logger.getAnonymousLogger().log(Level.INFO,"Finish order");
        userOrderService.addOrderToUser(token, userOrderDto);
        return "Order placed";
    }

}
