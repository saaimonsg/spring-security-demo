package org.example.springsecuritydemo.appuser.service;

import org.example.springsecuritydemo.appuser.data.RoleJpaRepository;
import org.example.springsecuritydemo.appuser.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleJpaRepository repository;

    @Override
    public Role create(Role role) {
        return repository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Role> findByUserId(String userId) {
        return List.of();
    }
}
