package com.example.petitionhub.mappers;

import com.example.petitionhub.models.TagEntity;
import com.example.petitionhub.tag.TagDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class TagEntityMapperImpl implements TagEntityMapper {

    @Override
    public TagEntity toTagEntity(TagDto dto) {
        if ( dto == null ) {
            return null;
        }

        TagEntity.TagEntityBuilder<?, ?> tagEntity = TagEntity.builder();

        tagEntity.name( dto.getName() );

        return tagEntity.build();
    }

    @Override
    public TagDto tagDto(TagEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setName( entity.getName() );

        return tagDto;
    }

    @Override
    public List<TagEntity> toTagEntities(List<TagDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<TagEntity> list = new ArrayList<TagEntity>( dtos.size() );
        for ( TagDto tagDto : dtos ) {
            list.add( toTagEntity( tagDto ) );
        }

        return list;
    }

    @Override
    public List<TagDto> toTagDtos(List<TagEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TagDto> list = new ArrayList<TagDto>( entities.size() );
        for ( TagEntity tagEntity : entities ) {
            list.add( tagDto( tagEntity ) );
        }

        return list;
    }
}
