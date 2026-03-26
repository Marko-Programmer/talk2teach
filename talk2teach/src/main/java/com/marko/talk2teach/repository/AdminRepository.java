package com.marko.talk2teach.repository;

import com.marko.talk2teach.model.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminProfile, Long> {

}