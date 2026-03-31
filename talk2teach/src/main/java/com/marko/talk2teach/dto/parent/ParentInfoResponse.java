package com.marko.talk2teach.dto.parent;

import com.marko.talk2teach.dto.child.ChildResponse;

import java.util.List;

public record ParentInfoResponse(
        Long id,
        String fullName,
        String email,
        String phone
) {}
