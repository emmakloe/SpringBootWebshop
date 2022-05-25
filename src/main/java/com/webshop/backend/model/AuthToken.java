package com.webshop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tokenId;
    private String token;
    @OneToOne(fetch = FetchType.LAZY)
    private AppUser user;
    private Date created;

    public AuthToken(AppUser user){
        this.user = user;
        this.created = new Date();
        this.token = UUID.randomUUID().toString();
    }
}
