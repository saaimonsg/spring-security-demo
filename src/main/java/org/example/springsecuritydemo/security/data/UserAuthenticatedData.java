package org.example.springsecuritydemo.security.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticatedData {
    private String username;
    private Collection<GrantedAuthority> permissions;
    private String base64EncodedAuthenticationKey;
}
