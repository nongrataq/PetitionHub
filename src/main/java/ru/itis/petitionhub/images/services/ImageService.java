package ru.itis.petitionhub.images.services;

import ru.itis.petitionhub.exceptions.NoSuchEntityException;
import ru.itis.petitionhub.petitions.model.PetitionEntity;
import ru.itis.petitionhub.images.repository.ImageRepository;
import ru.itis.petitionhub.images.enums.ImageType;
import ru.itis.petitionhub.images.model.ImageEntity;
import ru.itis.petitionhub.users.model.UserEntity;
import ru.itis.petitionhub.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    @Transactional
    public void uploadAvatarFromUser(MultipartFile avatarFile, UserEntity user) throws IOException {
        ImageEntity imageEntity = buildAvatarFromFile(avatarFile, user);
        imageRepository.save(imageEntity);
        user.setAvatar(imageEntity);
        userRepository.save(user);
    }

    public ImageEntity getAvatarById(UUID avatarId) {
        return imageRepository.findById(avatarId)
                .orElseThrow(() -> new NoSuchEntityException("Аватар с id" +  avatarId + " не существует"));
    }

    public ImageEntity buildAvatarFromFile(MultipartFile file, UserEntity user){
        try {
            return ImageEntity.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .imageType(ImageType.AVATAR)
                    .user(user)
                    .data(file.getBytes())
                    .build();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public ResponseEntity<?> getAvatar(UUID id) {
        ImageEntity avatar = getAvatarById(id);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .contentType(MediaType.parseMediaType(avatar.getFileType()))
                .contentLength(avatar.getData().length)
                .body(avatar.getData());
    }

    @Transactional
    public List<ImageEntity> uploadImagesForPetition(List<MultipartFile> files, PetitionEntity petition) {
        if (files.size() > 3) {
            throw new IllegalArgumentException("Максимум 3 изображения");
        }

        List<ImageEntity> result = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file == null || file.isEmpty() || file.getOriginalFilename() == null || file.getOriginalFilename().isEmpty()) {
                continue;
            }

            try {
                file.getBytes();
                if (file.getBytes().length == 0) {
                    continue;
                }

                ImageEntity image = ImageEntity.builder()
                        .fileName(file.getOriginalFilename())
                        .fileType(file.getContentType())
                        .data(file.getBytes())
                        .imageType(ImageType.PETITION)
                        .petition(petition)
                        .build();

                result.add(image);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        return imageRepository.saveAll(result);
    }
}
