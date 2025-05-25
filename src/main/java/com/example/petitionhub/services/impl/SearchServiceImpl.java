package com.example.petitionhub.services.impl;

import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.repositories.PetitionRepository;
import com.example.petitionhub.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {
    private final PetitionRepository petitionRepository;

    @Override
    public Page<PetitionEntity> searchPetitionByTitle(String query, Pageable pageable) {
        return petitionRepository.findByTitleContainingIgnoreCase(query, pageable);
    }
}
