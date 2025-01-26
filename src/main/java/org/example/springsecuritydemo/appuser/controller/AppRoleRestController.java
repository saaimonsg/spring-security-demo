package org.example.springsecuritydemo.appuser.controller;

import com.google.gson.Gson;
import org.example.springsecuritydemo.appuser.domain.Role;
import org.example.springsecuritydemo.appuser.service.RoleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/roles")
public class AppRoleRestController {


    private final RoleService roleService;
    private final Gson gson;

    public AppRoleRestController(RoleService roleService) {
        this.roleService = roleService;
        gson = new Gson();
    }


    @RequestMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public String createRole(@RequestBody Role role) {
        return gson.toJson(roleService.create(role));
    }


    @RequestMapping(value = "/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public String createRole(@PathVariable String userId) {
        if (userId != null) {
            return gson.toJson(roleService.findByUserId(userId));
        }
        return gson.toJson(roleService.findAll());
    }
@RequestMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public String getAll() {
        return gson.toJson(roleService.findAll());
    }


}
