package org.example.springsecuritydemo.appuser.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springsecuritydemo.appuser.data.AppUserData;
import org.example.springsecuritydemo.appuser.domain.AppUser;
import org.example.springsecuritydemo.appuser.exception.AppUserExceptionError;
import org.example.springsecuritydemo.appuser.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@Slf4j
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AppUser> fetchAll() {

        return appUserService.fetchAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AppUserData fetchOne(@PathVariable long id) throws AppUserExceptionError {
        return appUserService.fetchOne(id);
    }

    @RequestMapping(name = "user_create", value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String create(@RequestBody AppUser appUser) throws AppUserExceptionError {
        return appUserService.register(appUser);
    }


}
