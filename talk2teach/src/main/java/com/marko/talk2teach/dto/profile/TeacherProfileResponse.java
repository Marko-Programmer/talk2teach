package com.marko.talk2teach.dto.profile;

import java.util.List;

public record TeacherProfileResponse(
        Long id,
        String username,

        String fullName,
        String email,
        String phone,

        List<String> classRoom,
        List<String> subjects,
        String homeroomClass
) { }
