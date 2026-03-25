package com.marko.talk2teach.dto.timeslot;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TimeSlotRequest(
         @NotNull LocalDateTime startDateTime,
         @NotNull LocalDateTime endDateTime,
         @NotBlank String classRoom
) {}
