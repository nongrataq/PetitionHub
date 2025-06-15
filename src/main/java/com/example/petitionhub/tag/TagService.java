package com.example.petitionhub.tag;

import com.example.petitionhub.mappers.TagEntityMapper;
import com.example.petitionhub.models.TagEntity;
import com.example.petitionhub.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagEntityMapper tagEntityMapper;

    public List<TagDto> getAllTags() {
        return tagEntityMapper.toTagDtos(tagRepository.findAll());
    }

    public Optional<TagEntity> findTagByName(String name) {
        return tagRepository.findTagByName(name);
    }

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