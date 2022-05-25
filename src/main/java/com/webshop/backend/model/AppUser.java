package com.webshop.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webshop.backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;

    @Email
    @NotNull(message = "Email cannot be null!")
    private String email;

    @NotEmpty
    private String username;

    //private Role role = Role.USER;

    @NotEmpty
    @NotNull(message = "Password cannot be null!")
    @Size(min=8, message = "Password should be at least 8 characters!")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<UserOrder> userOrders;

    public AppUser(String email, String name, String password){
        this.email = email;
        this.username = name;
        this.password = password;
    }

    public AppUser(String email, String password){
        this.email = email;
        this.password = password;
    }

    public AppUser(String email, String name, String password, Role role){
        this.email = email;
        this.username = name;
        this.password = password;
        this.role = role;
    }
}

