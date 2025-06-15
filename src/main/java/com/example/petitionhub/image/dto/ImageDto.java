package com.example.petitionhub.image.dto;

import com.example.petitionhub.image.ImageTypes;
import lombok.*;

@Builder
@AllArgsConstructor
public class ImageDto {
    private ImageTypes imageType;
    private String fileName;
    private String fileType;
    private byte[] data;
}