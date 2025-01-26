package org.example.springsecuritydemo;

import lombok.RequiredArgsConstructor;
import org.example.springsecuritydemo.appuser.data.RoleJpaRepository;
import org.example.springsecuritydemo.appuser.domain.AppUser;
import org.example.springsecuritydemo.appuser.domain.Role;
import org.example.springsecuritydemo.appuser.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityDemoApplication implements CommandLineRunner {

    private final RoleJpaRepository roleJpaRepository;
    private final AppUserService appUserService;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Role role = new Role("ADMIN", "admin role", false);
        roleJpaRepository.save(role);
        AppUser appUser = new AppUser("admin", "admin", "admin@admin.com", "admin", "admin", role);
        appUserService.register(appUser);

    }
}
