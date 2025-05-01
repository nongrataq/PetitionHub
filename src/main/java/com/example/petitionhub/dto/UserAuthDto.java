package com.example.petitionhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthDto {

    @NotBlank(message = "Логин не может быть пустым")
    @Pattern(
            regexp = "^(?=[a-zA-Z])[a-zA-Z](?:(?!_{2})[a-zA-Z0-9_]){5,32}[a-zA-Z0-9]$",
            message = "Логин должен начинаться с буквы, не заканчиваться на _, быть 5-32 символа"
    )
    private String username;

    @NotBlank(message = "Пароль не может быть пустым")
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$",
            message = "Пароль должен быть от 8 до 20 символов, содержать хотя бы 1 заглавную букву, 1 строчную и 1 цифру и один спец. символ"
    )
    private String password;
}
