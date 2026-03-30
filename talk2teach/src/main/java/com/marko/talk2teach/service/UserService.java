package com.marko.talk2teach.service;

import com.marko.talk2teach.dto.auth.ChangePasswordRequest;
import com.marko.talk2teach.exception.InvalidPasswordException;
import com.marko.talk2teach.exception.ResourceNotFoundException;
import com.marko.talk2teach.model.User;
import com.marko.talk2teach.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void changePassword(Long userId, ChangePasswordRequest request) {

        if (!request.newPassword().equals(request.confirmPassword())) {
            throw new InvalidPasswordException("New passwords do not match");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " does not exist"));

        if (!user.getPassword().equals(request.oldPassword())) {
            throw new InvalidPasswordException("Old password is incorrect");
        }

        if (user.getPassword().equals(request.newPassword())) {
            throw new InvalidPasswordException("The new password cannot match the old one");
        }

        user.setPassword(request.newPassword());
        userRepository.save(user);
    }
}
