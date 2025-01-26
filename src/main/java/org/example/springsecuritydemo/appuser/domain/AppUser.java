package org.example.springsecuritydemo.appuser.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.springsecuritydemo.jpa.AbstractPersistableCustom;

@Entity
@Table(name = "app_user")
@Getter
@Setter
public class AppUser extends AbstractPersistableCustom {


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "app_appuser_role", joinColumns = @JoinColumn(name = "appuser_id"), inverseJoinColumns
            = @JoinColumn(name = "role_id"))
    private Role role;

    public AppUser() {
        //
    }

    public AppUser(String name, String surname, String email, String username, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
