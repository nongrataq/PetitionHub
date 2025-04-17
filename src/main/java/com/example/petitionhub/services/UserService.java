package com.example.petitionhub.services;

import com.example.petitionhub.entities.User;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public void registerUser(String username, String password) {
        System.out.println("Регистрация пользователя: " + username);
        if (userRepository.findUserByUsername(username).isPresent()) {
            throw new RuntimeException("Логин занят!");
        }

        userRepository.save(User.builder()
                .active(true)
                .password(passwordEncoder.encode(password))
                .username(username).build());
    }
}