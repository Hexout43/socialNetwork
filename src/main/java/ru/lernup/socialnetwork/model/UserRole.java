package ru.lernup.socialnetwork.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
public class UserRole implements GrantedAuthority {
  private final String role;

    public UserRole(String role) {
        this.role = "ROLE_" + role;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}
