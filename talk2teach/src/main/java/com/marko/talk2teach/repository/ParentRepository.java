package com.marko.talk2teach.repository;

import com.marko.talk2teach.model.ParentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository  extends JpaRepository<ParentProfile, Long> {

}