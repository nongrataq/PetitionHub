package com.example.petitionhub.services.impl;

import com.example.petitionhub.dto.SignUpDto;
import com.example.petitionhub.dto.UserDto;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.enums.Role;
import com.example.petitionhub.enums.Status;
import com.example.petitionhub.mappers.UserEntityMapper;
import com.example.petitionhub.repositories.UserRepository;
import com.example.petitionhub.services.SignUpService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoderBean;
    private final UserEntityMapper userEntityMapper;

    @Transactional
    @Override
    public UserDto signUp(SignUpDto signUpDto) {
        if (userRepository.existsUserEntityByUsername(signUpDto.getUsername())) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoderBean.encode(signUpDto.getPassword()))
                .role(Role.COMMON_USER)
                .status(Status.ACTIVE)
                .build();

        UserEntity savedUser = userRepository.save(userEntity);

        return userEntityMapper.toUserDto(savedUser);
    }
}