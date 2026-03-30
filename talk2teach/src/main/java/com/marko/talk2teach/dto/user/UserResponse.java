package com.marko.talk2teach.dto.user;

import com.marko.talk2teach.model.Role;

public record UserResponse(
        Long id,
        String username,
        Role role
) {}
