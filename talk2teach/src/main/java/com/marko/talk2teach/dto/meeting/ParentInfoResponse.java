package com.marko.talk2teach.dto.meeting;

public record ParentInfoResponse(
        Long id,
        String fullName,
        String email,
        String phone
) {}
