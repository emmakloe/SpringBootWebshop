package com.webshop.backend.service;

import com.webshop.backend.model.AppUser;
import com.webshop.backend.model.AuthToken;
import com.webshop.backend.repository.AuthTokenRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@NoArgsConstructor
@Transactional
public class AuthTokenService<appUser> {
    @Autowired
    private AuthTokenRepository authTokenRepository;

    public AppUser getUser(String token){return authTokenRepository.findByToken(token).getUser();}

    public AuthToken getToken(AppUser appUser){return authTokenRepository.findAuthTokenByUser(appUser);}
}
