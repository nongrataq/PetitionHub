package com.example.petitionhub.mappers;

import com.example.petitionhub.image.dto.ImageDto;
import com.example.petitionhub.models.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AvatarEntityMapper {
    ImageDto avatarEntityToAvatarDto(ImageEntity imageEntity);
    ImageEntity avatarDtoToAvatarEntity(ImageDto imageDto);
}
