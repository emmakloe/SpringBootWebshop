package com.webshop.backend.service;

import com.webshop.backend.dto.AppUser.LoginDto;
import com.webshop.backend.dto.AppUser.RegisterDto;
import com.webshop.backend.enums.Role;
import com.webshop.backend.model.AppUser;
import com.webshop.backend.model.AuthToken;
import com.webshop.backend.repository.AppUserRepository;
import com.webshop.backend.repository.AuthTokenRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@NoArgsConstructor
@Transactional
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthTokenRepository authTokenRepository;
    @Autowired
    private AuthTokenService authTokenService;


    public String register(RegisterDto registerDto) throws Exception {
        try{
            if(appUserRepository.findByEmail(registerDto.getEmail()) != null){
                Logger.getAnonymousLogger().log(Level.WARNING,"Email already registered");
                throw new Exception("Email already registered!");
            }
        }
        catch(Exception e){
            return "Email already registered";
        }
        String encryptedpassword = registerDto.getPassword();
        encryptedpassword = passwordEncoder.encode(registerDto.getPassword());
        AppUser user = new AppUser();
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(encryptedpassword);
        user.setRole(Role.USER);
        appUserRepository.save(user);
        AuthToken token = new AuthToken(user);
        authTokenRepository.save(token);
        return token.getToken();
    }

    public AppUser registerInitial(AppUser appUser) throws Exception {
        if(appUserRepository.findByEmail(appUser.getEmail()) != null){
            throw new Exception("Email already registered!");
        }
        String encryptedpassword = appUser.getPassword();
        encryptedpassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encryptedpassword);
        appUserRepository.save(appUser);
        AuthToken token = new AuthToken(appUser);
        authTokenRepository.save(token);
        return appUser;
    }

    public String login(LoginDto loginDto) throws Exception {
        if(appUserRepository.findByEmail(loginDto.getEmail()) == null){
            Logger.getAnonymousLogger().log(Level.WARNING,"User does not exist");
            throw new Exception("User does not exist");
        }
        if(passwordEncoder.matches(loginDto.getPassword(), appUserRepository.findByEmail(loginDto.getEmail()).getPassword()) == false){
            Logger.getAnonymousLogger().log(Level.WARNING,"Incorrect password");
            throw new Exception("Incorrect password");
        }
        AppUser  user = appUserRepository.findByEmail(loginDto.getEmail());
        AuthToken token = authTokenService.getToken(user);
        return token.getToken();
    }

    public String getRoleByEmail(String email){
        return appUserRepository.findByEmail(email).getRole().toString();
    }

}
