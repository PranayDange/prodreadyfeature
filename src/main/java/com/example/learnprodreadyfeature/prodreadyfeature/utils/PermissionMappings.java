package com.example.learnprodreadyfeature.prodreadyfeature.utils;

import com.example.learnprodreadyfeature.prodreadyfeature.entities.User;
import com.example.learnprodreadyfeature.prodreadyfeature.entities.enums.Permissions;
import com.example.learnprodreadyfeature.prodreadyfeature.entities.enums.Role;
import com.example.learnprodreadyfeature.prodreadyfeature.entities.enums.Role.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.learnprodreadyfeature.prodreadyfeature.entities.enums.Permissions.*;
import static com.example.learnprodreadyfeature.prodreadyfeature.entities.enums.Role.*;

public class PermissionMappings {

    private static final Map<Role, Set<Permissions>> map = Map.of(
            USER, Set.of(USER_VIEW, POST_VIEW),
            CREATOR, Set.of(POST_CREATE, USER_UPDATE, POST_DELETE),
            ADMIN, Set.of(POST_CREATE, USER_UPDATE, POST_UPDATE, USER_DELETE, USER_CREATE, POST_DELETE)

    );

    public static Set<SimpleGrantedAuthority> grantedAuthoritiesForRole(Role role) {
        return map.get(role).stream().map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .collect(Collectors.toSet());
    }
}
