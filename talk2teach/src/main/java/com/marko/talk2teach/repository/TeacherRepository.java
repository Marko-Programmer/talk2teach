package com.marko.talk2teach.repository;

import com.marko.talk2teach.model.TeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherProfile, Long> {

}