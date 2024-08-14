package com.example.learnprodreadyfeature.prodreadyfeature.entities;

import com.example.learnprodreadyfeature.prodreadyfeature.entities.enums.Permissions;
import com.example.learnprodreadyfeature.prodreadyfeature.entities.enums.Role;
import com.example.learnprodreadyfeature.prodreadyfeature.utils.PermissionMappings;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "app_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String pass;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

   /* @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Permissions> permissions;*/

    /*    @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Set<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                    .collect(Collectors.toSet());

            permissions.forEach(
                    permissions -> {
                        authorities.add(new SimpleGrantedAuthority(permissions.name()));
                    }
            );
            return authorities;
        }*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(
                role -> {
                    Set<SimpleGrantedAuthority> permissions = PermissionMappings.grantedAuthoritiesForRole(role);
                    authorities.addAll(permissions);
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
                }
        );
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.pass;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}

//entity(User) --> repository(UserRepository) -->service(user service)
