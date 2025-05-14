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
public class SignUpDto {
    @NotBlank(message = "Введите логин.")
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9_\\-.!+#$%*()^&]{5,32}$",
            message = "Логин должен быть 5-32 символа, содержать латинские буквы, спец. символы (_-.!+#$%*()^&)"
    )
    private String username;

    @NotBlank(message = "Введите пароль.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[0-9])(?=.*[#~?!$%()+^&*-_]).{6,20}$",
            message = "Пароль должен содержать: 6-20 символов, строчную букву, цифру и спецсимвол (#~?!$%()+^&*-_)"
    )
    private String password;
}
