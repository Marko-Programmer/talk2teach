package com.marko.talk2teach.service;

import com.marko.talk2teach.dto.timeslot.TimeSlotRequest;
import com.marko.talk2teach.dto.timeslot.TimeSlotResponse;
import com.marko.talk2teach.exception.ResourceNotFoundException;
import com.marko.talk2teach.exception.TimeSlotAlreadyBookedException;
import com.marko.talk2teach.mapper.TimeSlotMapper;
import com.marko.talk2teach.model.TeacherProfile;
import com.marko.talk2teach.model.TimeSlot;
import com.marko.talk2teach.repository.TeacherRepository;
import com.marko.talk2teach.repository.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotMapper timeSlotMapper;

    private final TeacherRepository teacherRepository;

    //    CREATE

    @Transactional
    public TimeSlotResponse createTimeSlot(Long userId, TimeSlotRequest timeSlotRequest) {
        TeacherProfile teacher = teacherRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher with User ID " + userId + " not found"));

        TimeSlot timeSlot = timeSlotMapper.toTimeSlot(timeSlotRequest, teacher);

        TimeSlot savedSlot = timeSlotRepository.save(timeSlot);

        return timeSlotMapper.toResponse(savedSlot);
    }

    //    READ

    @Transactional(readOnly = true)
    public List<TimeSlotResponse> getAllTimeSlots() {
        return timeSlotRepository.findAll().stream().map(timeSlotMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TimeSlotResponse getTimeSlotById(Long id) {
        TimeSlot response = timeSlotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TimeSlot with ID " + id + " not found"));

        return timeSlotMapper.toResponse(response);
    }

    @Transactional(readOnly = true)
    public List<TimeSlotResponse> getAvailableSlots(Long userId) {
        List<TimeSlot> timeSlots = timeSlotRepository.findByTeacher_UserIdAndIsBookedFalse(userId);

        return timeSlots.stream()
                .map(timeSlotMapper::toResponse)
                .toList();
    }

    //    DELETE

    @Transactional
    public void deleteTimeSlot(Long id) {
        TimeSlot timeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TimeSlot with ID " + id + " not found"));

        if (timeSlot.getIsBooked()) {
            throw new TimeSlotAlreadyBookedException("Cannot delete a booked time slot ID: " + id);
        }

        timeSlotRepository.delete(timeSlot);
    }




}