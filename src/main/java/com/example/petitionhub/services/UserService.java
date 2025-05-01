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
        return userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void saveUserToDataBase(UserEntity userEntity) {
        if (userRepository.existsUserEntityByUsername(userEntity.getUsername())) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }

        userRepository
                .save(UserEntity.builder()
                        .active(true)
                        .password(passwordEncoderBean.encode(userEntity.getPassword()))
                        .role("ROLE_USER")
                        .username(userEntity.getUsername()).build());
    }

}