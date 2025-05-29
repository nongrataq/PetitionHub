package com.example.petitionhub.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetitionDto {
    @Size(min = 5, max = 2500, message = "Описание от 5 до 2500 символов.")
    @Pattern(
            regexp = "^(?=.*[А-Яа-яA-Za-z])[\\s\\S]+$",
            message = "Описание должно содержать хотя бы одну букву (кириллицу или латиницу)."
    )
    private String description;

    @Size(min = 5, max = 255, message = "Заголовок от 5 до 255 символов.")
    @Pattern(
            regexp = "^(?=.*[А-Яа-яA-Za-z])[А-Яа-яA-Za-z0-9\\s.,!?\"'()\\-–—+=*#%@;^`~&$€£¥₽]+$",
            message = "Заголовок должен содержать буквы (кириллицу или латиницу), цифры и разрешённые символы."
    )
    private String title;

    @NotBlank(message = "Тег не может быть пустым.")
    @Size(min = 3, max = 30, message = "Размер тэга: 3-30 символов.")
    @Pattern(
            regexp = "^(?=.*[A-Za-zА-Яа-яёЁ])[A-Za-zА-Яа-яёЁ0-9_-]+$",
            message = "Разрешены буквы, цифры, дефисы и нижние подчёркивания. Обязательна хотя бы одна буква."
    )
    private String tagName;

    private UUID id;
    private LocalDateTime date;
    private String authorUsername;
    private int countOfSignatures;

}

