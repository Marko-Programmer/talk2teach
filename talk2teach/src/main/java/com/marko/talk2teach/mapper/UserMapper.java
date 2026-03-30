package com.marko.talk2teach.mapper;

import com.marko.talk2teach.dto.user.UserCreateRequest;
import com.marko.talk2teach.dto.user.UserResponse;
import com.marko.talk2teach.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toCreateUser(UserCreateRequest userCreateRequest){
        User user = new User();
        user.setUsername(userCreateRequest.username());
        user.setPassword(userCreateRequest.password());
        user.setRole(userCreateRequest.role());
        return user;
    }

    public UserResponse toResponse(User user){
        return new UserResponse(
          user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }

    public User toAddBaseInfo(String fullName, String email, String phone) {
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        return user;
    }
}
