package com.marko.talk2teach.dto.timeslot;

import java.time.LocalDateTime;

public record TimeSlotResponse(
        Long id,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        String classRoom,
        Boolean isBooked
) {}
