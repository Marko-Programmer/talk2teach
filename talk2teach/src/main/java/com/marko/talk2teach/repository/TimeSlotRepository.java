package com.marko.talk2teach.repository;

import com.marko.talk2teach.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    List<TimeSlot> findByTeacher_UserId(Long userId);

    List<TimeSlot> findByTeacher_UserIdAndIsBookedFalse(Long userId);

}