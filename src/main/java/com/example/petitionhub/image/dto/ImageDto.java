package com.example.petitionhub.image.dto;

import com.example.petitionhub.image.ImageType;
import lombok.*;

@Builder
@AllArgsConstructor
public class ImageDto {
    private ImageType imageType;
    private String fileName;
    private String fileType;
    private byte[] data;
}