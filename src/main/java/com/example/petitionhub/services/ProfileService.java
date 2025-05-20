package com.example.petitionhub.services;

import com.example.petitionhub.dto.PetitionDto;

import java.util.List;

public interface ProfileService {
    List<PetitionDto> findPetitionsByAuthor_Username(String authorUsername);
}