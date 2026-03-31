package com.marko.talk2teach.mapper;

import com.marko.talk2teach.dto.teacher.TeacherInfoResponse;
import com.marko.talk2teach.dto.teacher.TeacherProfileResponse;
import com.marko.talk2teach.dto.teacher.TeacherRegisterRequest;
import com.marko.talk2teach.dto.teacher.TeacherUpdateRequest;
import com.marko.talk2teach.model.TeacherProfile;
import com.marko.talk2teach.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherMapper {

    private final UserMapper userMapper;

    // Request

    public TeacherProfile toTeacherProfile(TeacherRegisterRequest request, User existingUser) {

        existingUser.setFullName(request.fullName());
        existingUser.setPhone(request.phone());

        TeacherProfile teacherProfile = new TeacherProfile();
        teacherProfile.setUser(existingUser);

        teacherProfile.setClassRoom(request.classRoom());
        teacherProfile.setSubjects(request.subjects());
        teacherProfile.setHomeroomClass(request.homeroomClass());

        return teacherProfile;
    }


    public void updateTeacherFromRequest(TeacherProfile existingProfile, TeacherUpdateRequest request) {
        if (request == null) return;

        User user = existingProfile.getUser();
        if (user != null) {
            if (request.fullName() != null && !request.fullName().isBlank()) {
                user.setFullName(request.fullName());
            }
            if (request.phone() != null && !request.phone().isBlank()) {
                user.setPhone(request.phone());
            }
            if (request.email() != null && !request.email().isBlank()) {
                user.setEmail(request.email());
            }
        }

        if (request.classRoom() != null) {
            existingProfile.setClassRoom(request.classRoom());
        }

        if (request.subjects() != null) {
            existingProfile.setSubjects(request.subjects());
        }

        if (request.homeroomClass() != null) {
            existingProfile.setHomeroomClass(request.homeroomClass());
        }
    }


    // Response

    public TeacherProfileResponse toTeacherProfileResponse(TeacherProfile teacherProfile) {
        return new TeacherProfileResponse(
                teacherProfile.getUser().getId(),
                teacherProfile.getUser().getUsername(),

                teacherProfile.getId(),
                teacherProfile.getUser().getFullName(),
                teacherProfile.getUser().getEmail(),
                teacherProfile.getUser().getPhone(),

                teacherProfile.getClassRoom(),
                teacherProfile.getSubjects(),
                teacherProfile.getHomeroomClass()
        );
    }


    public TeacherInfoResponse toTeacherInfoResponse(TeacherProfile teacherProfile) {
        return new TeacherInfoResponse(
                teacherProfile.getId(),

                teacherProfile.getUser().getFullName(),
                teacherProfile.getUser().getEmail(),
                teacherProfile.getUser().getPhone(),

                teacherProfile.getClassRoom(),
                teacherProfile.getSubjects(),
                teacherProfile.getHomeroomClass()
        );
    }

}
