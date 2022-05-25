package com.webshop.backend.controller;

import com.webshop.backend.dto.AppUser.LoginDto;
import com.webshop.backend.dto.AppUser.LoginResponseDto;
import com.webshop.backend.dto.AppUser.RegisterDto;
import com.webshop.backend.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
@RequestMapping("/api")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;


    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> Register(@RequestBody RegisterDto registerDto) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"User register");
        return new ResponseEntity<>(appUserService.register(registerDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> Login(@RequestBody LoginDto loginDto) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO,"User login");
        String token = appUserService.login(loginDto);
        String role = appUserService.getRoleByEmail(loginDto.getEmail());
        return new ResponseEntity<>(new LoginResponseDto(token,role), HttpStatus.OK);
    }
}
