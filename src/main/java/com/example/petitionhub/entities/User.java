package com.example.petitionhub.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "имя не может быть пустым!")
    @Column(unique = true, nullable = false, name = "login")
    private String username;
    @NotBlank(message = "пароль не может быть пустым!")
    @Column(nullable = false, name = "password")
    private String password;
    @Column(columnDefinition = "boolean default true")
    private boolean active;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*TODO: добавить роль (ROLE_USER)*/
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}