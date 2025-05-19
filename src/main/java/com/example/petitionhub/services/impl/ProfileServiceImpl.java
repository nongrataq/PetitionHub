package com.example.petitionhub.services.impl;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.mappers.PetitionEntityMapper;
import com.example.petitionhub.repositories.PetitionRepository;
import com.example.petitionhub.repositories.UserRepository;
import com.example.petitionhub.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;
    private final PetitionEntityMapper petitionEntityMapper;

    @Override
    public List<PetitionDto> findPetitionsByAuthor_Username(String authorUsername) {
        return petitionEntityMapper
                .toPetitionDtos(petitionRepository.findPetitionEntitiesByAuthor_Username(authorUsername));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsUserEntityByUsername(username);
    }
}
