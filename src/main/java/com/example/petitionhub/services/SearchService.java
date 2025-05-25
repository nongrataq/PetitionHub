package com.example.petitionhub.services;

import com.example.petitionhub.entities.PetitionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {
    Page<PetitionEntity> searchPetitionByTitle(String query, Pageable page);
}
