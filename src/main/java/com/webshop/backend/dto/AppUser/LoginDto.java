package com.webshop.backend.dto.AppUser;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
