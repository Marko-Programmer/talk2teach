package com.marko.talk2teach.dto.teacher;

import java.util.List;

public record TeacherUpdateRequest(
        String fullName,
        String email,
        String phone,
        List<String> classRoom,
        List<String> subjects,
        String homeroomClass
) {}
