package com.marko.talk2teach.repository;

import com.marko.talk2teach.model.Meeting;
import com.marko.talk2teach.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    // teachers

    List<Meeting> findByTimeSlot_Teacher_UserId(Long userId);

    List<Meeting> findByTimeSlot_Teacher_UserIdAndTimeSlot_StartDateTimeBetween(
            Long userId, LocalDateTime start, LocalDateTime end);


    // parents

    List<Meeting> findByParent_UserId(Long userId);


    // general / admins

    List<Meeting> findByTimeSlot_StartDateTimeBetween(LocalDateTime start, LocalDateTime end);


    // scheduler

    @Modifying
    @Query("UPDATE Meeting m SET m.status = :newStatus " +
           "WHERE m.status = :oldStatus AND m.timeSlot.endDateTime < :now")
    void updateStatusForExpiredMeetings(Status newStatus, Status oldStatus, LocalDateTime now);
}