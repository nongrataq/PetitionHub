package com.example.petitionhub.tag;

import com.example.petitionhub.models.TagEntity;
import com.example.petitionhub.repositories.TagRepository;
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