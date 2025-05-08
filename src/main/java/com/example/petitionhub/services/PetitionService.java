package com.example.petitionhub.services;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.UserEntity;
import java.util.List;

public interface PetitionService {
    PetitionDto createPetition(PetitionDto petition);
    List<PetitionEntity> findAllByAuthor(UserEntity author);
}
