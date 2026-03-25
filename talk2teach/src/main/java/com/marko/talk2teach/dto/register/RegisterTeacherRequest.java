package com.marko.talk2teach.dto.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record RegisterTeacherRequest(
        @NotBlank String fullName,
        String email,
        String phone,

        @NotEmpty List<String> classRoom,
        @NotEmpty List<String> subjects,
        String homeroomClass
) {}
