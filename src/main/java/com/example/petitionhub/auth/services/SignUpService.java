package com.example.petitionhub.auth.services;

import com.example.petitionhub.auth.dto.SignUpDto;
import com.example.petitionhub.models.UserEntity;
import com.example.petitionhub.auth.enums.Role;
import com.example.petitionhub.auth.enums.Status;
import com.example.petitionhub.exceptions.UserAccountAlreadyExistsException;
import com.example.petitionhub.repositories.UserRepository;
import com.example.petitionhub.user.dto.UserDto;
import com.example.petitionhub.mappers.UserEntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoderBean;
    private final UserEntityMapper userEntityMapper;

    @Transactional
    public UserDto signUp(SignUpDto signUpDto) {
        if (userRepository.existsUserEntityByUsername(signUpDto.getUsername())) {
            log.warn("Attempt to register with existing username: {}", signUpDto.getUsername());
            throw new UserAccountAlreadyExistsException("Пользователь с таким именем уже существует");
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
