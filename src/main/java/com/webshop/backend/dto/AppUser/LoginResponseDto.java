package com.webshop.backend.dto.AppUser;

import lombok.Data;

@Data
public class LoginResponseDto {
    String token;
    String role;

    public LoginResponseDto(String token, String role) {
        this.token = token;
        this.role = role;
    }
}
