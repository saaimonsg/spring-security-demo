package org.example.springsecuritydemo.appuser.data;

import org.example.springsecuritydemo.appuser.domain.AppUser;
import org.example.springsecuritydemo.appuser.domain.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUserRowMapper implements RowMapper<AppUser> {



    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppUser appUser = new AppUser();
        appUser.setId(rs.getLong("id"));
        appUser.setEmail(rs.getString("email"));
        appUser.setName(rs.getString("name"));
        appUser.setPassword(rs.getString("password"));
        appUser.setSurname(rs.getString("surname"));
        appUser.setUsername(rs.getString("username"));
        Role role = new Role(
                rs.getString("name"),
                rs.getString("description"),
                rs.getBoolean("is_disabled"));
        appUser.setRole(role);

        return appUser;
    }
}

