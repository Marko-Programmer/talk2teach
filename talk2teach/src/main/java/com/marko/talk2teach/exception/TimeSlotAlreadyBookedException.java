package com.marko.talk2teach.exception;

public class TimeSlotAlreadyBookedException extends RuntimeException {
    public TimeSlotAlreadyBookedException(String message) {
        super(message);
    }
}
