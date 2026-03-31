package com.marko.talk2teach.controller;

import com.marko.talk2teach.dto.timeslot.TimeSlotRequest;
import com.marko.talk2teach.dto.timeslot.TimeSlotResponse;
import com.marko.talk2teach.service.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timeslots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    //    CREATE

    @PostMapping("/teacher/{userId}")
    public ResponseEntity<TimeSlotResponse> createTimeSlot(
            @PathVariable Long userId,
            @RequestBody TimeSlotRequest timeSlotRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(timeSlotService.createTimeSlot(userId, timeSlotRequest));
    }

    // READ

    @GetMapping
    public ResponseEntity<List<TimeSlotResponse>> getAllTimeSlots() {
        return ResponseEntity.ok(timeSlotService.getAllTimeSlots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeSlotResponse> getTimeSlotById(@PathVariable Long id) {
        return ResponseEntity.ok(timeSlotService.getTimeSlotById(id));
    }

    @GetMapping("/{userId}/available-slots")
    public ResponseEntity<List<TimeSlotResponse>> getAvailableSlots(@PathVariable Long userId) {
        return ResponseEntity.ok(timeSlotService.getAvailableSlots(userId));
    }

    //    DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeSlot(@PathVariable Long id) {
        timeSlotService.deleteTimeSlot(id);
        return ResponseEntity.noContent().build();
    }

}
