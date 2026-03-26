package com.marko.talk2teach.dto.parent;

import com.marko.talk2teach.dto.child.ChildResponse;

import java.util.List;

public record ParentProfileResponse(
        Long userId,
        String username,

        Long profileId,
        String fullName,
        String email,
        String phone,

        List<ChildResponse> children
) { }
