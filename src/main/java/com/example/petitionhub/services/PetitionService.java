package com.example.petitionhub.services;

import com.example.petitionhub.dto.PetitionCreationResultDto;
import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface PetitionService {
    PetitionCreationResultDto createPetitionWithTimeCheck(PetitionDto petitionDto);
    Page<PetitionEntity> findAll(Pageable pageable);
    PetitionEntity findPetitionById(UUID id);
}
