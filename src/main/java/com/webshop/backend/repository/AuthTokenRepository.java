package com.webshop.backend.repository;

import com.webshop.backend.model.AppUser;
import com.webshop.backend.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
    AuthToken findByToken(String token);

    AuthToken findAuthTokenByUser(AppUser user);
}
