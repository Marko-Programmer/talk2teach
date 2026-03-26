package com.marko.talk2teach.mapper;

import com.marko.talk2teach.dto.timeslot.TimeSlotRequest;
import com.marko.talk2teach.dto.timeslot.TimeSlotResponse;
import com.marko.talk2teach.model.TeacherProfile;
import com.marko.talk2teach.model.TimeSlot;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeSlotMapper {

    public TimeSlot toTimeSlot(TimeSlotRequest request, TeacherProfile teacher) {
        if (request == null) return null;

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setStartDateTime(request.startDateTime());
        timeSlot.setEndDateTime(request.endDateTime());
        timeSlot.setClassRoom(request.classRoom());

        timeSlot.setTeacher(teacher);

        return timeSlot;
    }

    public TimeSlotResponse toResponse(TimeSlot timeSlot){
        if (timeSlot == null) return null;

        return new TimeSlotResponse(
            timeSlot.getId(),
            timeSlot.getStartDateTime(),
            timeSlot.getEndDateTime(),
            timeSlot.getClassRoom(),
            timeSlot.getIsBooked());
    }

}
