package com.marko.talk2teach.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordUpdateRequest(
        @NotBlank
        String oldPassword,

        @NotBlank
        @Size(min = 4)
        String newPassword,

        @NotBlank
        String confirmPassword
) {}
