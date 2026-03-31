package com.marko.talk2teach.controller;

import com.marko.talk2teach.dto.meeting.MeetingResponse;
import com.marko.talk2teach.dto.parent.ParentInfoResponse;
import com.marko.talk2teach.dto.parent.ParentProfileResponse;
import com.marko.talk2teach.dto.parent.ParentRegisterRequest;
import com.marko.talk2teach.dto.parent.ParentUpdateRequest;
import com.marko.talk2teach.service.ParentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parent")
public class ParentController {

    private final ParentService parentService;

    // Create

    @PostMapping("/create/{userId}")
    public ResponseEntity<ParentProfileResponse> createParent(@Valid @RequestBody ParentRegisterRequest request,
                                                               @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parentService.createParent(request, userId));
    }

    // Read

    @GetMapping("/{parentId}")
    public ResponseEntity<ParentInfoResponse> getParentById(@PathVariable Long parentId) {
        return ResponseEntity.ok(parentService.getParentById(parentId));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ParentProfileResponse> getMyProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(parentService.getMyProfile(userId));
    }

    @GetMapping("/{userId}/myMeetings")
    public ResponseEntity<List<MeetingResponse>> getMyMeetings(@PathVariable Long userId) {
        return ResponseEntity.ok(parentService.getMyMeetings(userId));
    }

    // Update

    @PatchMapping("/{userId}/update")
    public ResponseEntity<ParentProfileResponse> updateParent(@PathVariable Long userId,
                                                                @Valid @RequestBody ParentUpdateRequest request) {
        return ResponseEntity.ok(parentService.updateParent(userId, request));
    }

}