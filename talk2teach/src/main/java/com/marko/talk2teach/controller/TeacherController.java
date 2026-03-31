package com.marko.talk2teach.controller;

import com.marko.talk2teach.dto.meeting.MeetingResponse;
import com.marko.talk2teach.dto.teacher.TeacherInfoResponse;
import com.marko.talk2teach.dto.teacher.TeacherProfileResponse;
import com.marko.talk2teach.dto.teacher.TeacherRegisterRequest;
import com.marko.talk2teach.dto.teacher.TeacherUpdateRequest;
import com.marko.talk2teach.dto.timeslot.TimeSlotResponse;
import com.marko.talk2teach.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    // Create

    @PostMapping("/create/{userId}")
    public ResponseEntity<TeacherProfileResponse> createTeacher(@Valid @RequestBody TeacherRegisterRequest request,
                                                                @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(teacherService.createTeacher(request, userId));
    }

    // Read

    @GetMapping
    public ResponseEntity<List<TeacherInfoResponse>> searchTeachers(
            @RequestParam(required = false) List<String> subjects,
            @RequestParam(required = false) String fullName
     ) {
        List<TeacherInfoResponse> response = teacherService.searchTeachers(subjects, fullName);
        return ResponseEntity.ok(response);
     }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherInfoResponse> getTeacherById(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherService.getTeacherById(teacherId));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<TeacherProfileResponse> getMyProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(teacherService.getMyProfile(userId));
    }

    @GetMapping("/{userId}/timeslots")
    public ResponseEntity<List<TimeSlotResponse>> getMySlots(@PathVariable Long userId) {
        return ResponseEntity.ok(teacherService.getMySlots(userId));
    }

    @GetMapping("/{userId}/meetings")
    public ResponseEntity<List<MeetingResponse>> getMyMeetings(@PathVariable Long userId,
                                                               @RequestParam(required = false)
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(teacherService.getMyMeetings(userId, date));
    }

    // Update

    @PatchMapping("/{userId}/update")
    public ResponseEntity<TeacherProfileResponse> updateTeacher(@PathVariable Long userId,
                                                @Valid @RequestBody TeacherUpdateRequest request) {
        return ResponseEntity.ok(teacherService.updateTeacher(userId, request));
    }



}