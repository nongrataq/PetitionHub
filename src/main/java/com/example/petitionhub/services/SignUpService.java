package com.example.petitionhub.services;

import com.example.petitionhub.dto.SignUpDto;
import com.example.petitionhub.dto.UserDto;
import com.example.petitionhub.entities.UserEntity;

public interface SignUpService {
    UserDto signUp(SignUpDto userEntity);
}
