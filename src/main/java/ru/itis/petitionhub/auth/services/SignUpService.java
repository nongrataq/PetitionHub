package ru.itis.petitionhub.auth.services;

import ru.itis.petitionhub.auth.dto.SignUpDto;
import ru.itis.petitionhub.users.model.UserEntity;
import ru.itis.petitionhub.auth.enums.Role;
import ru.itis.petitionhub.auth.enums.Status;
import ru.itis.petitionhub.exceptions.UserAccountAlreadyExistsException;
import ru.itis.petitionhub.users.repository.UserRepository;
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

    @Transactional
    public void signUp(SignUpDto signUpDto) {
        if (userRepository.existsUserEntityByUsername(signUpDto.getUsername())) {
            throw new UserAccountAlreadyExistsException("Пользователь с таким именем уже существует");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoderBean.encode(signUpDto.getPassword()))
                .role(Role.COMMON_USER)
                .status(Status.ACTIVE)
                .build();

        userRepository.save(userEntity);
    }
}
