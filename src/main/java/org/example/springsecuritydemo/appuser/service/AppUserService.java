package org.example.springsecuritydemo.appuser.service;


import org.example.springsecuritydemo.appuser.data.AppUserData;
import org.example.springsecuritydemo.appuser.exception.AppUserExceptionError;
import org.example.springsecuritydemo.appuser.domain.AppUser;

import java.util.List;

public interface AppUserService {
    String register(AppUser appUser) throws AppUserExceptionError;
    AppUserData fetchOne(Long id) throws AppUserExceptionError;
    List<AppUser> fetchAll();
}
