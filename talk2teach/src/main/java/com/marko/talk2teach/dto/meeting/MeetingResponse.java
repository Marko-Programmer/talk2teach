package com.marko.talk2teach.dto.meeting;

import com.marko.talk2teach.dto.timeslot.TimeSlotResponse;
import com.marko.talk2teach.model.Status;

import java.time.LocalDateTime;

public record MeetingResponse(

        Long id,

        TimeSlotResponse timeSlotResponse,
        TeacherInfoResponse teacher,
        ParentInfoResponse parent,
        ChildInfoResponse child,

        String message,
        Status status,
        LocalDateTime createdAt
) {
}
