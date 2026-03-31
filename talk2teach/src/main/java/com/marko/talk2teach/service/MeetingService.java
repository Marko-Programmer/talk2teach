package com.marko.talk2teach.service;

import com.marko.talk2teach.dto.meeting.MeetingRequest;
import com.marko.talk2teach.dto.meeting.MeetingResponse;
import com.marko.talk2teach.exception.MeetingStatusException;
import com.marko.talk2teach.exception.ResourceNotFoundException;
import com.marko.talk2teach.exception.TimeSlotAlreadyBookedException;
import com.marko.talk2teach.mapper.ChildMapper;
import com.marko.talk2teach.mapper.MeetingMapper;
import com.marko.talk2teach.model.*;
import com.marko.talk2teach.repository.ChildRepository;
import com.marko.talk2teach.repository.MeetingRepository;
import com.marko.talk2teach.repository.ParentRepository;
import com.marko.talk2teach.repository.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;

    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;
    private final TimeSlotRepository timeSlotRepository;

    // CREATE

    @Transactional
    public MeetingResponse createMeeting(Long userId, MeetingRequest request) {
        ParentProfile parentProfile = parentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Parent with user ID " + userId + " not found"));

        Child child = childRepository.findById(request.childId())
                .orElseThrow(() -> new ResourceNotFoundException("Child not found"));

        TimeSlot timeSlot = timeSlotRepository.findById(request.timeSlotId())
                .orElseThrow(() -> new ResourceNotFoundException("TimeSlot not found"));

        if (timeSlot.getIsBooked()){
            throw new TimeSlotAlreadyBookedException("TimeSlot already booked");
        }

        timeSlot.setIsBooked(true);

        Meeting meeting = meetingMapper.toMeeting(request, timeSlot, parentProfile, child);

        return meetingMapper.toMeetingResponse(meetingRepository.save(meeting));
    }

    // READ

    @Transactional(readOnly = true)
    public List<MeetingResponse> searchMeetings(LocalDate date) {
        List<Meeting> meetings;

        if (date != null) {
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
            meetings = meetingRepository.findByTimeSlot_StartDateTimeBetween(startOfDay, endOfDay);
        } else {
            meetings = meetingRepository.findAll();
        }

        return meetings.stream()
                .map(meetingMapper::toMeetingResponse)
                .toList();
    }

    // UPDATE

    @Transactional
    public void cancelMeeting(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting with ID " + meetingId + " not found"));

        if (meeting.getStatus() == Status.CANCELLED) {
            throw new MeetingStatusException("Meeting is already canceled");
        }

        meeting.setStatus(Status.CANCELLED);
        meeting.getTimeSlot().setIsBooked(false);
    }

}
