package org.example.springsecuritydemo.appuser.data;


import org.example.springsecuritydemo.appuser.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserJpaRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
    AppUser findByEmail(String email);
}
