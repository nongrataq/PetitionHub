package com.example.petitionhub.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetitionDto {
    @NotBlank(message = "Введите описание.")
    @Size(max = 3500, message="Описание не должно превышать 3500 символов")
    @Pattern(
            regexp = "^[A-Za-zА-Яа-яЁё0-9\\s.,!?\\-—()«»:;'\"&]{10,3500}$",
            message = "Текст петиции должен содержать от 10 до 5000 символов (буквы, цифры, пробелы, знаки препинания)."
    )
    private String description;

    @NotBlank(message = "Введите заголовок.")
    @Size(max = 255, message = "Заголовок не должен превышать 255 символов.")
    @Pattern(
            regexp = "^[A-Za-zА-Яа-яЁё0-9\\s.,!?\\-—()«»:;'\"&]{5,255}$",
            message = "Название должно содержать 5-255 символов (буквы, цифры, пробелы, знаки препинания)."
    )
    private String title;
}
