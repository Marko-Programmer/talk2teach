package com.marko.talk2teach.service;

import com.marko.talk2teach.dto.user.UserCreateRequest;
import com.marko.talk2teach.dto.user.UserResponse;
import com.marko.talk2teach.exception.ResourceNotFoundException;
import com.marko.talk2teach.mapper.UserMapper;
import com.marko.talk2teach.model.Role;
import com.marko.talk2teach.model.User;
import com.marko.talk2teach.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        User newUser = userMapper.toCreateUser(request);
        User savedUser = userRepository.save(newUser);
        return userMapper.toResponse(savedUser);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers(Role role) {
        if (role != null) {
            return userRepository.findAllByRole(role).stream()
                    .map(userMapper::toResponse)
                    .toList();
        } else {
            return userRepository.findAll().stream()
                    .map(userMapper::toResponse)
                    .toList();
        }
    }

    @Transactional
    public String deleteUser(Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));

        userRepository.delete(user);
        return "User with ID " + userId + " was deleted successfully";
    }

}