package com.marko.talk2teach.service;

import com.marko.talk2teach.dto.meeting.MeetingResponse;
import com.marko.talk2teach.dto.parent.ParentInfoResponse;
import com.marko.talk2teach.dto.parent.ParentProfileResponse;
import com.marko.talk2teach.dto.parent.ParentRegisterRequest;
import com.marko.talk2teach.dto.parent.ParentUpdateRequest;
import com.marko.talk2teach.exception.ResourceNotFoundException;
import com.marko.talk2teach.mapper.MeetingMapper;
import com.marko.talk2teach.mapper.ParentMapper;
import com.marko.talk2teach.model.Meeting;
import com.marko.talk2teach.model.ParentProfile;
import com.marko.talk2teach.model.User;
import com.marko.talk2teach.repository.MeetingRepository;
import com.marko.talk2teach.repository.ParentRepository;
import com.marko.talk2teach.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final UserRepository userRepository;

    private final ParentRepository parentRepository;
    private final ParentMapper parentMapper;

    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;


    // Create

    @Transactional
    public ParentProfileResponse createParent(ParentRegisterRequest request, Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));

        ParentProfile parentProfile = parentMapper.toParentProfile(request, existingUser);

        return parentMapper.toParentProfileResponse(parentRepository.save(parentProfile));
    }

    // Read

    @Transactional(readOnly = true)
    public ParentInfoResponse getParentById(Long parentId) {
        ParentProfile parentProfile = parentRepository.findById(parentId)
                .orElseThrow(() -> new ResourceNotFoundException("Parent with ID " + parentId + " not found"));

        return parentMapper.toParentInfoResponse(parentProfile);
    }

    @Transactional(readOnly = true)
    public ParentProfileResponse getMyProfile(Long userId) {
        ParentProfile parentProfile = parentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Parent with User ID " + userId + " not found"));

        return parentMapper.toParentProfileResponse(parentProfile);
    }

    @Transactional(readOnly = true)
    public List<MeetingResponse> getMyMeetings(Long userId) {
        List<Meeting> meetings = meetingRepository.findByParent_UserId(userId);

        return meetings.stream()
                .map(meetingMapper::toMeetingResponse)
                .toList();
    }

    // Update

    @Transactional
    public ParentProfileResponse updateParent(Long userId, ParentUpdateRequest request) {
        ParentProfile existingProfile = parentRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Parent with User ID " + userId + " not found"));

        parentMapper.updateParentFromRequest(existingProfile, request);

        return parentMapper.toParentProfileResponse(existingProfile);
    }


}