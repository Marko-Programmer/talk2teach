package com.marko.talk2teach.dto.meeting;


import jakarta.validation.constraints.NotNull;

public record MeetingCreateRequest(
        @NotNull Long timeSlotId,
        @NotNull Long childId,

        String message
) {}
