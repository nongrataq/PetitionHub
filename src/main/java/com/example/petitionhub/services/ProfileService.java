package com.example.petitionhub.services;

import com.example.petitionhub.dto.projections.PetitionProjection;

import java.util.List;

public interface ProfileService {
    List<PetitionProjection> findPetitionsByAuthor_Username(String authorUsername);
}