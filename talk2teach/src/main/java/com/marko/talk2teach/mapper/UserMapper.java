package com.marko.talk2teach.mapper;

import com.marko.talk2teach.dto.admin.UserCreateRequest;
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

    public User toAddBaseInfo(String fullName, String email, String phone) {
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        return user;
    }
}
