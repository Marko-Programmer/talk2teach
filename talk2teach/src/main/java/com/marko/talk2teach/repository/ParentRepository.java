package com.marko.talk2teach.repository;

import com.marko.talk2teach.model.ParentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository  extends JpaRepository<ParentProfile, Long> {

    Optional<ParentProfile> findByUserId(Long userId);

}