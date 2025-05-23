package com.example.petitionhub.services.impl;

import com.example.petitionhub.dto.PetitionDto;
import com.example.petitionhub.entities.PetitionEntity;
import com.example.petitionhub.entities.SignatureEntity;
import com.example.petitionhub.entities.UserEntity;
import com.example.petitionhub.mappers.PetitionEntityMapper;
import com.example.petitionhub.repositories.PetitionRepository;
import com.example.petitionhub.repositories.SignatureRepository;
import com.example.petitionhub.repositories.UserRepository;
import com.example.petitionhub.services.PetitionService;
import com.example.petitionhub.services.SignService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class PetitionServiceImpl implements PetitionService {
    private final PetitionRepository petitionRepository;
    private final UserRepository userRepository;
    private final PetitionEntityMapper petitionEntityMapper;


    @Override
    @Transactional
    public PetitionDto createPetition(PetitionDto petitionDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        PetitionEntity petition = petitionEntityMapper.toPetitionEntity(petitionDto);
        petition.setAuthor(userEntity);

        PetitionEntity savedPetition = petitionRepository.save(petition);

        return petitionEntityMapper.toPetitionDto(savedPetition);
    }

    @Override
    public PetitionEntity findPetitionById(UUID id) {
        return petitionRepository.findById(id).orElseThrow(() -> new NullPointerException("Несуществующая петиция"));
    }

    @Override
    public List<PetitionEntity> searchPetitionByTitle(String query) {
        return petitionRepository.findByTitleContainingIgnoreCase(query);
    }

    @Override
    public Page<PetitionEntity> findAll(Pageable pageable) {
        return petitionRepository.findAll(pageable);
    }

}
