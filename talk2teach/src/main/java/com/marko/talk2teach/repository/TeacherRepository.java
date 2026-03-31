package com.marko.talk2teach.repository;

import com.marko.talk2teach.model.TeacherProfile;
import com.marko.talk2teach.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherProfile, Long> {

    @Query("SELECT t FROM TeacherProfile t JOIN t.user u " +
            "WHERE (:fullName IS NULL OR LOWER(u.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))) " +
            "AND (:subjects IS NULL OR EXISTS (SELECT s FROM t.subjects s WHERE s IN :subjects))")
    List<TeacherProfile> findWithFilter(
            @Param("subjects") List<String> subjects,
            @Param("fullName") String fullName
    );

    Optional<TeacherProfile> findByUserId(Long userId);
}