package com.webshop.backend.model;

import com.webshop.backend.dto.Order.UserOrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "userorder")
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private AppUser user;

    @NotEmpty
    @NotNull(message = "Country cannot be null!")
    private String country;

    @NotNull
    @PositiveOrZero
    @NotNull(message = "Zipcode cannot be null!")
    private int zipcode;

    @NotEmpty
    @NotNull(message = "City cannot be null!")
    private String city;

    @NotEmpty
    @NotNull(message = "Street cannot be null!")
    private String street;

    @NotNull
    @PositiveOrZero
    private int houseNumber;

    @NotNull
    private double totalPrice;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String name;

    public UserOrder(UserOrderDto userOrderDto){
        this.city = userOrderDto.getCity();
        this.country = userOrderDto.getCountry();
        this.zipcode = userOrderDto.getZipcode();
        this.street = userOrderDto.getStreet();
        this.houseNumber = userOrderDto.getHouseNumber();
        this.name = userOrderDto.getName();
        this.surname = userOrderDto.getSurname();
        this.totalPrice = userOrderDto.getTotalPrice();
    }
}
