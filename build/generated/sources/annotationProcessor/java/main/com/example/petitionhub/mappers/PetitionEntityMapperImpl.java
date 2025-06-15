package com.example.petitionhub.mappers;

import com.example.petitionhub.models.PetitionEntity;
import com.example.petitionhub.models.TagEntity;
import com.example.petitionhub.models.UserEntity;
import com.example.petitionhub.petition.dto.PetitionDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class PetitionEntityMapperImpl implements PetitionEntityMapper {

    @Override
    public PetitionDto toPetitionDto(PetitionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PetitionDto.PetitionDtoBuilder petitionDto = PetitionDto.builder();

        petitionDto.authorUsername( entityAuthorUsername( entity ) );
        petitionDto.tagName( entityTagEntityName( entity ) );
        petitionDto.description( entity.getDescription() );
        petitionDto.title( entity.getTitle() );
        petitionDto.id( entity.getId() );
        petitionDto.date( entity.getDate() );
        if ( entity.getCountOfSignatures() != null ) {
            petitionDto.countOfSignatures( entity.getCountOfSignatures() );
        }

        return petitionDto.build();
    }

    @Override
    public PetitionEntity toPetitionEntity(PetitionDto dto) {
        if ( dto == null ) {
            return null;
        }

        PetitionEntity.PetitionEntityBuilder<?, ?> petitionEntity = PetitionEntity.builder();

        petitionEntity.id( dto.getId() );
        petitionEntity.date( dto.getDate() );
        petitionEntity.title( dto.getTitle() );
        petitionEntity.description( dto.getDescription() );
        petitionEntity.countOfSignatures( dto.getCountOfSignatures() );

        return petitionEntity.build();
    }

    private String entityAuthorUsername(PetitionEntity petitionEntity) {
        if ( petitionEntity == null ) {
            return null;
        }
        UserEntity author = petitionEntity.getAuthor();
        if ( author == null ) {
            return null;
        }
        String username = author.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String entityTagEntityName(PetitionEntity petitionEntity) {
        if ( petitionEntity == null ) {
            return null;
        }
        TagEntity tagEntity = petitionEntity.getTagEntity();
        if ( tagEntity == null ) {
            return null;
        }
        String name = tagEntity.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
