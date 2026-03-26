package com.marko.talk2teach.dto.admin;

public record AdminProfileResponse(
        Long userId,
        String username,

        Long profileId
) {}
