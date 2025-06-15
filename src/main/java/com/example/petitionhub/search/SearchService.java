package com.example.petitionhub.search;

import com.example.petitionhub.petitions.projections.PetitionProjection;
import com.example.petitionhub.repositories.PetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SearchService {
    private final PetitionRepository petitionRepository;

    public Page<PetitionProjection> searchPetitionByTitle(String query, Pageable pageable) {
        return petitionRepository.findByTitleContainingIgnoreCase(query, pageable);
    }

    public Page<PetitionProjection> searchPetitionByTag(String tag, Pageable pageable) {
        return petitionRepository.findByTagEntityNameContainingIgnoreCase(tag, pageable);
    }
}
