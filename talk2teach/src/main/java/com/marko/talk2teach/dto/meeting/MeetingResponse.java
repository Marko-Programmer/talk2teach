package com.marko.talk2teach.dto.meeting;

import com.marko.talk2teach.dto.child.ChildResponse;
import com.marko.talk2teach.dto.parent.ParentInfoResponse;
import com.marko.talk2teach.dto.teacher.TeacherInfoResponse;
import com.marko.talk2teach.dto.timeslot.TimeSlotResponse;
import com.marko.talk2teach.model.Status;

import java.time.LocalDateTime;

public record MeetingResponse(

        Long id,

        TimeSlotResponse timeSlotResponse,
        TeacherInfoResponse teacher,
        ParentInfoResponse parent,
        ChildResponse child,

        String message,
        Status status,
        LocalDateTime createdAt
) {
}
