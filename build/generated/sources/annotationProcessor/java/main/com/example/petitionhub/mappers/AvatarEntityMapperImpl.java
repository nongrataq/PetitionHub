package com.example.petitionhub.mappers;

import com.example.petitionhub.image.dto.ImageDto;
import com.example.petitionhub.models.ImageEntity;
import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class AvatarEntityMapperImpl implements AvatarEntityMapper {

    @Override
    public ImageDto avatarEntityToAvatarDto(ImageEntity imageEntity) {
        if ( imageEntity == null ) {
            return null;
        }

        ImageDto.ImageDtoBuilder imageDto = ImageDto.builder();

        imageDto.imageType( imageEntity.getImageType() );
        imageDto.fileName( imageEntity.getFileName() );
        imageDto.fileType( imageEntity.getFileType() );
        byte[] data = imageEntity.getData();
        if ( data != null ) {
            imageDto.data( Arrays.copyOf( data, data.length ) );
        }

        return imageDto.build();
    }

    @Override
    public ImageEntity avatarDtoToAvatarEntity(ImageDto imageDto) {
        if ( imageDto == null ) {
            return null;
        }

        ImageEntity.ImageEntityBuilder<?, ?> imageEntity = ImageEntity.builder();

        return imageEntity.build();
    }
}
