package com.marko.talk2teach.mapper;

import com.marko.talk2teach.dto.meeting.MeetingRequest;
import com.marko.talk2teach.dto.meeting.MeetingResponse;
import com.marko.talk2teach.model.Child;
import com.marko.talk2teach.model.Meeting;
import com.marko.talk2teach.model.ParentProfile;
import com.marko.talk2teach.model.TimeSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingMapper {

    private final TimeSlotMapper timeSlotMapper;
    private final TeacherMapper teacherMapper;
    private final ParentMapper parentMapper;
    private final ChildMapper childMapper;

    public Meeting toMeeting(MeetingRequest request, TimeSlot slot, ParentProfile parent, Child child) {
        if (request == null || slot == null || parent == null) {
            return null;
        }

        Meeting meeting = new Meeting();
        meeting.setTimeSlot(slot);
        meeting.setParent(parent);
        meeting.setChild(child);
        meeting.setMessage(request.message());
        return meeting;
    }


    public MeetingResponse toMeetingResponse(Meeting meeting) {
        if (meeting == null) return null;

        return new MeetingResponse(
                meeting.getId(),

                timeSlotMapper.toResponse(meeting.getTimeSlot()),
                teacherMapper.toTeacherInfoResponse(meeting.getTimeSlot().getTeacher()),
                parentMapper.toParentInfoResponse(meeting.getParent()),
                childMapper.toChildResponse(meeting.getChild()),

                meeting.getMessage(),
                meeting.getStatus(),
                meeting.getCreatedAt()
        );
    }

}
