package com.marko.talk2teach.dto.profile;

import com.marko.talk2teach.dto.meeting.ChildInfoResponse;

import java.util.List;

public record ParentProfileResponse(
        Long id,
        String username,

        String fullName,
        String email,
        String phone,

        List<ChildInfoResponse> children
) { }
