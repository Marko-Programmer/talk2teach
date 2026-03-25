package com.marko.talk2teach.dto.register;

import jakarta.validation.constraints.NotBlank;

public record ChildRequest(
         @NotBlank String fullName,
         @NotBlank String classGroup
) {}
