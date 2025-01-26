package org.example.springsecuritydemo.security.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springsecuritydemo.appuser.data.AppUserJpaRepository;
import org.example.springsecuritydemo.appuser.domain.AppUser;
import org.example.springsecuritydemo.appuser.exception.AppUserExceptionError;
import org.example.springsecuritydemo.appuser.service.AppUserService;
import org.example.springsecuritydemo.security.data.UserAuthenticatedData;
import org.example.springsecuritydemo.security.data.UserAuthenticationData;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RootApiController {
    private final AuthenticationManager authenticationManager;
    private final AppUserJpaRepository userRepository;
    private final AppUserService appUserService;

    @RequestMapping(name = "Authentication", value = "/authenticate",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public String authenticate(@RequestBody String jsonString) {

        Gson gson = new Gson();
        UserAuthenticationData request = gson.fromJson(jsonString, UserAuthenticationData.class);

        if (request == null) {
            return "No user authentication data provided";
        }

        String username = request.getUsername();
        String password = request.getPassword();
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        Authentication authenticated = this.authenticationManager.authenticate(authenticationRequest);

        final Collection<GrantedAuthority> permissions = new ArrayList<>();
        AppUser user = userRepository.findByUsername(username);
        permissions.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().getName()));
        final byte[] base64EncodedAuthenticationKey = Base64.getEncoder()
                .encode((username + ":" + password).getBytes(StandardCharsets.UTF_8));

        UserAuthenticatedData userAuthenticatedData = new UserAuthenticatedData(username,
                permissions, new String(base64EncodedAuthenticationKey, StandardCharsets.UTF_8));
        if (authenticated.isAuthenticated()) {
            if(SecurityContextHolder.getContext().getAuthentication().equals(authenticated)){
                log.info("User already Authenticated");
            }
            return gson.toJson(userAuthenticatedData);
        }
        return gson.toJson(new UserAuthenticatedData());
    }

    @RequestMapping( value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String save(@RequestBody AppUser appUser) throws AppUserExceptionError {

        return appUserService.register(appUser);
    }

    
}
