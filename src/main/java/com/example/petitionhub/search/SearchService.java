package com.example.petitionhub.search;

import com.example.petitionhub.petition.projections.PetitionProjection;
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
}
