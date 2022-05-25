package com.webshop.backend.dto.Order;

import lombok.Data;

@Data
public class UserOrderDto {
    private String surname;
    private String name;
    private int houseNumber;
    private double totalPrice;
    private String city;
    private String country;
    private int zipcode;
    private String street;
}
