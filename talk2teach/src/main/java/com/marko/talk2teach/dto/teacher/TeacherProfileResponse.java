package com.marko.talk2teach.dto.teacher;

import java.util.List;

public record TeacherProfileResponse(
        Long userId,
        String username,

        Long profileId,
        String fullName,
        String email,
        String phone,

        List<String> classRoom,
        List<String> subjects,
        String homeroomClass
) { }
