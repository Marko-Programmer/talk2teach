package com.marko.talk2teach.dto.parent;

import com.marko.talk2teach.dto.child.ChildRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ParentRegisterRequest(
        @NotBlank(message = "Your name is required")
        String fullName,
        String email,
        String phone,

        @NotEmpty(message = "Your should have a child")
        List<ChildRequest> children
) {}
