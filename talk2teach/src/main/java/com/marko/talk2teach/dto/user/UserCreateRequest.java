package com.marko.talk2teach.dto.user;

import com.marko.talk2teach.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotBlank
        @Size(min = 4, max = 30)
        String username,

        @NotBlank
        @Size(min = 4)
        String password,

        @NotNull
        Role role
) {}
