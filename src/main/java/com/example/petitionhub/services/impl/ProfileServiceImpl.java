package com.example.petitionhub.services.impl;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.dto.projections.PetitionProjection;
import com.example.petitionhub.mappers.PetitionEntityMapper;
import com.example.petitionhub.repositories.PetitionRepository;
import com.example.petitionhub.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {
    private final PetitionRepository petitionRepository;

    @Override
    public List<PetitionProjection> findPetitionsByAuthor_Username(String authorUsername) {
        return petitionRepository.findPetitionEntitiesByAuthor_Username(authorUsername);
    }
}