package com.example.petitionhub.services;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.UserEntity;
import java.util.List;
import java.util.UUID;

public interface PetitionService {
    PetitionDto createPetition(PetitionDto petition);
    List<PetitionDto> findAllByAuthor(UserEntity author);
    List<PetitionEntity> findAll();
    PetitionEntity findPetitionById(UUID id);
}
