package com.marko.talk2teach.mapper;

import com.marko.talk2teach.dto.admin.AdminProfileResponse;
import com.marko.talk2teach.model.AdminProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminMapper {

    private final UserMapper userMapper;

    public AdminProfileResponse toAdminProfileResponse(AdminProfile adminProfile) {
        return new AdminProfileResponse(
                adminProfile.getUser().getId(),
                adminProfile.getUser().getUsername(),

                adminProfile.getId()
        );
    }

}
