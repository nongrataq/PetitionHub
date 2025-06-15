package com.example.petitionhub.image.services;

import com.example.petitionhub.exceptions.NoSuchEntityException;
import com.example.petitionhub.models.PetitionEntity;
import com.example.petitionhub.repositories.ImageRepository;
import com.example.petitionhub.image.ImageTypes;
import com.example.petitionhub.models.ImageEntity;
import com.example.petitionhub.models.UserEntity;
import com.example.petitionhub.repositories.UserRepository;
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
    public void uploadAvatarFromUser(MultipartFile avatarFile, UserEntity user, ImageTypes type) throws IOException {
        ImageEntity imageEntity = buildAvatarFromFile(avatarFile, type);
        imageRepository.save(imageEntity);
        user.setAvatar(imageEntity);
        userRepository.save(user);
    }

    public ImageEntity getAvatarById(UUID avatarId) {
        return imageRepository.findById(avatarId)
                .orElseThrow(() -> new NoSuchEntityException("Аватар с id" +  avatarId + " не существует"));
    }

    public ImageEntity buildAvatarFromFile(MultipartFile file, ImageTypes imageType) throws IOException {
        return ImageEntity.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .data(file.getBytes())
                .imageType(imageType)
                .build();
    }

    public ResponseEntity<?> getAvatar(UUID id) {
        ImageEntity avatar = getAvatarById(id);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .contentType(MediaType.parseMediaType(avatar.getFileType()))
                .contentLength(avatar.getData().length)
                .body(avatar.getData());
    }

    public List<ImageEntity> uploadImagesForPetition(List<MultipartFile> files, PetitionEntity petition) throws IOException {
        if (files.size() > 5) {
            throw new IllegalArgumentException("Максимум 5 изображений");
        }

        List<ImageEntity> result = new ArrayList<>();
        for (MultipartFile file : files) {
            ImageEntity image = ImageEntity.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .data(file.getBytes())
                    .imageType(ImageTypes.PETITION)
                    .petition(petition)
                    .build();

            result.add(imageRepository.save(image));
        }
        return result;
    }

}
