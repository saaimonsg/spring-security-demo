package org.example.springsecuritydemo.appuser.service;


import org.example.springsecuritydemo.appuser.domain.Role;

import java.util.List;

public interface RoleService {
    Role create(Role roleData);
    List<Role> findAll();
    List<Role> findByUserId(String userId);
}
