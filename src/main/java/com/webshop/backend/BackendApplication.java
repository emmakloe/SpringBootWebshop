package com.webshop.backend;

import com.webshop.backend.enums.Role;
import com.webshop.backend.model.AppUser;
import com.webshop.backend.model.Item;
import com.webshop.backend.repository.AppUserRepository;
import com.webshop.backend.repository.ItemRepository;
import com.webshop.backend.service.AppUserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            appUserService.registerInitial(new AppUser("user@gmail.com","user","useruseruser", Role.USER));
            appUserService.registerInitial(new AppUser("admin@gmail.com","admin","adminadminadmin", Role.ADMIN));
            itemRepository.save(new Item("Orbit", 500));
            itemRepository.save(new Item("Airwaves",400));
            itemRepository.save(new Item("Hubabuba", 800));
        };
    }

}
