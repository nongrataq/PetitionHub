package com.example.petitionhub.mappers;

import com.example.petitionhub.models.TagEntity;
import com.example.petitionhub.tag.TagDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagEntityMapper {
    TagEntity toTagEntity(TagDto dto);
    TagDto tagDto(TagEntity entity);
    List<TagEntity> toTagEntities(List<TagDto> dtos);
    List<TagDto> toTagDtos(List<TagEntity> entities);
}
