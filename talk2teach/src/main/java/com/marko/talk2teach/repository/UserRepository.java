package com.marko.talk2teach.repository;

import com.marko.talk2teach.model.Role;
import com.marko.talk2teach.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRole(Role role);

}