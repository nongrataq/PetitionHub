package com.example.petitionhub.utils;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.dto.UserAuthDto;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.UserEntity;
import org.springframework.stereotype.Service;


@Service
public class MappingUtils {
    public UserEntity mapUserAuthDtoToUserEntity(UserAuthDto dto) {
        if (dto == null) return null;

        return UserEntity.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role("ROLE_USER")
                .active(true)
                .build();
    }

    public PetitionEntity mapPetitionDtoToPetitionEntity(PetitionDto dto) {
        if (dto == null) return null;

        return PetitionEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }
}