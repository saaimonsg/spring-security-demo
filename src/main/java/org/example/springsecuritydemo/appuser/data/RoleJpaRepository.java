package org.example.springsecuritydemo.appuser.data;

import org.example.springsecuritydemo.appuser.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role,Long> {
}
