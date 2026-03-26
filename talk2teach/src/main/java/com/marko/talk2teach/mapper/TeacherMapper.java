package com.marko.talk2teach.mapper;

import com.marko.talk2teach.dto.teacher.TeacherInfoResponse;
import com.marko.talk2teach.dto.teacher.TeacherProfileResponse;
import com.marko.talk2teach.dto.teacher.TeacherRegisterRequest;
import com.marko.talk2teach.model.TeacherProfile;
import com.marko.talk2teach.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherMapper {

    private final UserMapper userMapper;

    public TeacherProfile toTeacherProfile(TeacherRegisterRequest request) {
        User user = userMapper.toAddBaseInfo(
                request.fullName(),
                request.email(),
                request.phone());

        TeacherProfile teacherProfile = new TeacherProfile();
        teacherProfile.setUser(user);

        teacherProfile.setClassRoom(request.classRoom());
        teacherProfile.setSubjects(request.subjects());
        teacherProfile.setHomeroomClass(request.homeroomClass());

        return teacherProfile;
    }



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
