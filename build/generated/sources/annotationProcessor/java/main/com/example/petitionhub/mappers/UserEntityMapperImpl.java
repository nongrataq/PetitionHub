package com.example.petitionhub.mappers;

import com.example.petitionhub.models.PetitionEntity;
import com.example.petitionhub.models.UserEntity;
import com.example.petitionhub.petition.dto.PetitionDto;
import com.example.petitionhub.user.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserEntityMapperImpl implements UserEntityMapper {

    @Override
    public UserEntity toUserEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder<?, ?> userEntity = UserEntity.builder();

        userEntity.username( userDto.getUsername() );
        userEntity.petitions( petitionDtoListToPetitionEntityList( userDto.getPetitions() ) );

        return userEntity.build();
    }

    @Override
    public UserDto toUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.username( userEntity.getUsername() );
        userDto.petitions( petitionEntityListToPetitionDtoList( userEntity.getPetitions() ) );

        return userDto.build();
    }

    protected PetitionEntity petitionDtoToPetitionEntity(PetitionDto petitionDto) {
        if ( petitionDto == null ) {
            return null;
        }

        PetitionEntity.PetitionEntityBuilder<?, ?> petitionEntity = PetitionEntity.builder();

        petitionEntity.id( petitionDto.getId() );
        petitionEntity.date( petitionDto.getDate() );
        petitionEntity.title( petitionDto.getTitle() );
        petitionEntity.description( petitionDto.getDescription() );
        petitionEntity.countOfSignatures( petitionDto.getCountOfSignatures() );

        return petitionEntity.build();
    }

    protected List<PetitionEntity> petitionDtoListToPetitionEntityList(List<PetitionDto> list) {
        if ( list == null ) {
            return null;
        }

        List<PetitionEntity> list1 = new ArrayList<PetitionEntity>( list.size() );
        for ( PetitionDto petitionDto : list ) {
            list1.add( petitionDtoToPetitionEntity( petitionDto ) );
        }

        return list1;
    }

    protected PetitionDto petitionEntityToPetitionDto(PetitionEntity petitionEntity) {
        if ( petitionEntity == null ) {
            return null;
        }

        PetitionDto.PetitionDtoBuilder petitionDto = PetitionDto.builder();

        petitionDto.description( petitionEntity.getDescription() );
        petitionDto.title( petitionEntity.getTitle() );
        petitionDto.id( petitionEntity.getId() );
        petitionDto.date( petitionEntity.getDate() );
        if ( petitionEntity.getCountOfSignatures() != null ) {
            petitionDto.countOfSignatures( petitionEntity.getCountOfSignatures() );
        }

        return petitionDto.build();
    }

    protected List<PetitionDto> petitionEntityListToPetitionDtoList(List<PetitionEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<PetitionDto> list1 = new ArrayList<PetitionDto>( list.size() );
        for ( PetitionEntity petitionEntity : list ) {
            list1.add( petitionEntityToPetitionDto( petitionEntity ) );
        }

        return list1;
    }
}
