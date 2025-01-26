package org.example.springsecuritydemo.appuser.service;

import org.example.springsecuritydemo.appuser.data.AppUserData;
import org.example.springsecuritydemo.appuser.data.AppUserJpaRepository;
import org.example.springsecuritydemo.appuser.data.AppUserRowMapper;
import org.example.springsecuritydemo.appuser.data.RoleJpaRepository;
import org.example.springsecuritydemo.appuser.domain.Role;
import org.example.springsecuritydemo.appuser.exception.AppUserExceptionError;
import org.example.springsecuritydemo.appuser.domain.AppUser;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl implements AppUserService {

    private final AppUserJpaRepository appUserJpaRepository;
    private final RoleJpaRepository roleJpaRepository;
    private final JdbcTemplate jdbcTemplate;


    @Override
    public String register(AppUser appUser) throws AppUserExceptionError {


        AppUser userByUsername = appUserJpaRepository.findByUsername(appUser.getUsername());
        if (userByUsername != null) {
            throw new AppUserExceptionError("user.already.exists");
        }

        userByUsername = appUserJpaRepository.findByEmail(appUser.getEmail());
        if (userByUsername != null) {
            throw new AppUserExceptionError("user.email.already.exists");
        }

        long id = 1L;
        if(appUser.getRole() != null ){
            id = appUser.getRole().getId();
        }
        Role role = roleJpaRepository.findById(id).orElse(null);
        if (role == null) {
            throw new AppUserExceptionError("role.not.found");
        }

        String password = new BCryptPasswordEncoder().encode(appUser.getPassword());

        AppUser user = new AppUser(appUser.getName(), appUser.getSurname(), appUser.getEmail(),
                appUser.getUsername(), password,role);
        appUserJpaRepository.save(user);


        appUser.setPassword("");
        return new Gson().toJson(appUser);
    }

    @Override
    public AppUserData fetchOne(Long id) throws AppUserExceptionError {
        Optional<AppUser> userOptional = appUserJpaRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new AppUserExceptionError("user.not.exists");
        }
        return new AppUserData(userOptional.get());
    }

    public List<AppUser> fetchAll() {
        // Implementation of RowMapper interface
        AppUserRowMapper appUserRowMapper = new AppUserRowMapper();
        return jdbcTemplate.query("SELECT * FROM app_user left join db_default.app_role role on role.id = (select role_id from db_default.app_appuser_role aar where app_user.id = aar.appuser_id)",
                appUserRowMapper);
    }




}
