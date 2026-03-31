package com.marko.talk2teach.scheduler;

import com.marko.talk2teach.model.Status;
import com.marko.talk2teach.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MeetingStatusScheduler {

    private final MeetingRepository meetingRepository;

    @Scheduled(fixedRate = 900000)
    @Transactional
    public void autoUpdateMeetingStatus() {
        LocalDateTime now = LocalDateTime.now();

        meetingRepository.updateStatusForExpiredMeetings(Status.ENDED, Status.PLANNED, now);
    }

}
