package com.example.petitionhub.services;

import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoderBean;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public void save(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }
        userRepository
                .save(UserEntity.builder()
                .isActive(true)
                .password(passwordEncoderBean.encode(password))
                        .role("ROLE_USER")
                .username(username).build());
    }

    public UserEntity findUserEntityByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }


}