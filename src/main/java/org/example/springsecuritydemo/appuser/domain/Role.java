package org.example.springsecuritydemo.appuser.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.springsecuritydemo.jpa.AbstractPersistableCustom;

import java.io.Serializable;

@Entity
@Table(name = "app_role")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Role extends AbstractPersistableCustom implements Serializable {

    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;


}

