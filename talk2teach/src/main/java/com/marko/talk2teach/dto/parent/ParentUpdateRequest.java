package com.marko.talk2teach.dto.parent;

import com.marko.talk2teach.dto.child.ChildRequest;

import java.util.List;

public record ParentUpdateRequest(
        String fullName,
        String email,
        String phone,
        List<ChildRequest> children
) {}
