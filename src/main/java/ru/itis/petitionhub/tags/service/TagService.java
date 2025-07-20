package ru.itis.petitionhub.tags.service;

import ru.itis.petitionhub.tags.model.TagEntity;
import ru.itis.petitionhub.tags.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public TagEntity getOrCreateTagByName(String tagName) {
        String normalizedTagName = tagName.trim();

        return tagRepository.findTagByName(normalizedTagName)
                .orElseGet(() -> tagRepository.save(
                        TagEntity.builder()
                                .name(normalizedTagName)
                                .build()
                ));
    }
}