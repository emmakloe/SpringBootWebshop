package com.webshop.backend.dto.AppUser;

import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String username;
    private String password;
}
