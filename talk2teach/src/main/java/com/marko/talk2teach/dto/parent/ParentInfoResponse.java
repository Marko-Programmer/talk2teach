package com.marko.talk2teach.dto.parent;

public record ParentInfoResponse(
        Long id,
        String fullName,
        String email,
        String phone
) {}
