package ru.itis.petitionhub.petitions.services;

import ru.itis.petitionhub.images.services.ImageService;
import ru.itis.petitionhub.petitions.model.PetitionEntity;
import ru.itis.petitionhub.users.model.UserEntity;
import ru.itis.petitionhub.exceptions.NoSuchEntityException;
import ru.itis.petitionhub.mappers.PetitionEntityMapper;
import ru.itis.petitionhub.petitions.dto.PetitionDto;
import ru.itis.petitionhub.petitions.projections.PetitionProjection;
import ru.itis.petitionhub.petitions.repository.PetitionRepository;
import ru.itis.petitionhub.users.repository.UserRepository;
import ru.itis.petitionhub.tags.service.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Service
public class PetitionService {
    private static final long PETITION_CREATION_COOLDOWN_SECONDS = 20;
    private final PetitionRepository petitionRepository;
    private final UserRepository userRepository;
    private final PetitionEntityMapper mapper;
    private final TagService tagService;
    private final ImageService imageService;

    @Transactional
    public void savePetition(PetitionDto dto, List<MultipartFile> images) {
        UserEntity user = getCurrentUser();
        PetitionEntity petition = mapper.toPetitionEntity(dto);
        petition.setAuthor(getCurrentUser());
        petition.setTagEntity(tagService.getOrCreateTagByName(dto.getTagName()));
        petition.setImages(imageService.uploadImagesForPetition(images, petition));
        user.getPetitions().add(petition);
        petitionRepository.save(petition);
    }

    public void incrementSignatureCount(PetitionEntity petition) {
        petitionRepository.incrementSignatureCount(petition.getId());
        petitionRepository.save(petition);
    }

    private UserEntity getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public long getRemainingCooldown() {
        UserEntity user = getCurrentUser();
        Optional<LocalDateTime> lastDate = petitionRepository.findLastPetitionDateByUser(user);

        if (lastDate.isEmpty()) return 0;

        long secondsPassed = Duration.between(lastDate.get(), LocalDateTime.now()).getSeconds();
        return Math.max(0, PETITION_CREATION_COOLDOWN_SECONDS - secondsPassed);
    }


    public boolean canCreatePetitionNow() {
        return getRemainingCooldown() == 0;
    }


    public PetitionEntity findPetitionById(UUID id) {
        return petitionRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("Несуществующая петиция"));
    }


    public List<PetitionProjection> findPetitionsByAuthorUsername(String authorUsername) {
        return petitionRepository.findPetitionEntitiesByAuthor_Username(authorUsername);
    }


    public Page<PetitionProjection> findAllProjections(Pageable pageable) {
        return petitionRepository.findAllProjections(pageable);
    }
}
