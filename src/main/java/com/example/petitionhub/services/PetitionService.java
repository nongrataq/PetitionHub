package com.example.petitionhub.services;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PetitionService {
    PetitionDto createPetition(PetitionDto petition);
    Page<PetitionEntity> findAll(Pageable pageable);
    PetitionEntity findPetitionById(UUID id);
    List<PetitionEntity> searchPetitionByTitle(String query);
}
