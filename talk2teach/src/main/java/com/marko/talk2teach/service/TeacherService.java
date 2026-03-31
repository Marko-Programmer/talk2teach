package com.marko.talk2teach.service;

import com.marko.talk2teach.dto.meeting.MeetingResponse;
import com.marko.talk2teach.dto.teacher.TeacherInfoResponse;
import com.marko.talk2teach.dto.teacher.TeacherProfileResponse;
import com.marko.talk2teach.dto.teacher.TeacherRegisterRequest;
import com.marko.talk2teach.dto.teacher.TeacherUpdateRequest;
import com.marko.talk2teach.dto.timeslot.TimeSlotResponse;
import com.marko.talk2teach.exception.ResourceNotFoundException;
import com.marko.talk2teach.mapper.MeetingMapper;
import com.marko.talk2teach.mapper.TeacherMapper;
import com.marko.talk2teach.mapper.TimeSlotMapper;
import com.marko.talk2teach.model.Meeting;
import com.marko.talk2teach.model.TeacherProfile;
import com.marko.talk2teach.model.TimeSlot;
import com.marko.talk2teach.model.User;
import com.marko.talk2teach.repository.MeetingRepository;
import com.marko.talk2teach.repository.TeacherRepository;
import com.marko.talk2teach.repository.TimeSlotRepository;
import com.marko.talk2teach.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final UserRepository userRepository;

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotMapper timeSlotMapper;

    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;

    // Create

    @Transactional
    public TeacherProfileResponse createTeacher(TeacherRegisterRequest request, Long userId) {
        User existingUser = userRepository.getById(userId);

        TeacherProfile teacherProfile = teacherMapper.toTeacherProfile(request, existingUser);

        return teacherMapper.toTeacherProfileResponse(teacherRepository.save(teacherProfile));
    }

    // Read

    @Transactional(readOnly = true)
    public List<TeacherInfoResponse> searchTeachers(List<String> subjects, String fullName) {
        List<TeacherProfile> teachers = teacherRepository.findWithFilter(subjects, fullName);

        return teachers.stream()
                .map(teacherMapper::toTeacherInfoResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TeacherInfoResponse getTeacherById(Long teacherId) {
        TeacherProfile teacherProfile = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher with ID " + teacherId + " not found"));

        return teacherMapper.toTeacherInfoResponse(teacherProfile);
    }

    @Transactional(readOnly = true)
    public TeacherProfileResponse getMyProfile(Long userId) {
        TeacherProfile teacherProfile = teacherRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher with User ID " + userId + " not found"));

        return teacherMapper.toTeacherProfileResponse(teacherProfile);
    }

    @Transactional(readOnly = true)
    public List<TimeSlotResponse> getMySlots(Long userId) {
        List<TimeSlot> timeSlots = timeSlotRepository.findByTeacher_UserId(userId);

        return timeSlots.stream()
                .map(timeSlotMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MeetingResponse> getMyMeetings(Long userId, LocalDate date) {
        List<Meeting> meetings;

        if (date != null) {
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
            meetings = meetingRepository.findByTimeSlot_Teacher_UserIdAndTimeSlot_StartDateTimeBetween(
                    userId, startOfDay, endOfDay);
        } else {
            meetings = meetingRepository.findByTimeSlot_Teacher_UserId(userId);
        }

        return meetings.stream()
                .map(meetingMapper::toMeetingResponse)
                .toList();
    }

    // Update

    @Transactional
    public TeacherProfileResponse updateTeacher(Long userId, TeacherUpdateRequest request) {
        TeacherProfile existingProfile = teacherRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher with User ID " + userId + " not found"));

        teacherMapper.updateTeacherFromRequest(existingProfile, request);

        return teacherMapper.toTeacherProfileResponse(existingProfile);
    }

}