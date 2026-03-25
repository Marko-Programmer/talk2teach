package com.marko.talk2teach.dto.meeting;

import java.util.List;

public record TeacherInfoResponse(
        Long id,
        String fullName,
        String email,
        String phone,

        List<String> classRoom,
        List<String> subject,
        String homeroomClass
) {}
