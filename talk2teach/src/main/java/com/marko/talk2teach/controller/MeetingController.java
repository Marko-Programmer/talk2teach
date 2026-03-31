package com.marko.talk2teach.controller;

import com.marko.talk2teach.dto.meeting.MeetingRequest;
import com.marko.talk2teach.dto.meeting.MeetingResponse;
import com.marko.talk2teach.service.MeetingService;
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
@RequestMapping("/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    // CREATE

    @PostMapping("/{userId}")
    public ResponseEntity<MeetingResponse> createMeeting(@PathVariable Long userId,
                                                         @Valid @RequestBody MeetingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(meetingService.createMeeting(userId, request));
    }

    // READ

    @GetMapping("/search")
    public ResponseEntity<List<MeetingResponse>> searchMeetings(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok(meetingService.searchMeetings(date));
    }

    //    UPDATE

    @PatchMapping("/{meetingId}/cancel")
    public ResponseEntity<Void> cancelMeeting(@PathVariable Long meetingId) {
        meetingService.cancelMeeting(meetingId);
        return ResponseEntity.noContent().build();
    }

}
