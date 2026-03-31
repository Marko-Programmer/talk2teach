package com.marko.talk2teach.mapper;

import com.marko.talk2teach.dto.child.ChildRequest;
import com.marko.talk2teach.dto.child.ChildResponse;
import com.marko.talk2teach.dto.parent.ParentInfoResponse;
import com.marko.talk2teach.dto.parent.ParentProfileResponse;
import com.marko.talk2teach.dto.parent.ParentRegisterRequest;
import com.marko.talk2teach.dto.parent.ParentUpdateRequest;
import com.marko.talk2teach.dto.teacher.TeacherUpdateRequest;
import com.marko.talk2teach.model.Child;
import com.marko.talk2teach.model.ParentProfile;
import com.marko.talk2teach.model.TeacherProfile;
import com.marko.talk2teach.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ParentMapper {

    private final ChildMapper childMapper;

    // Request

    public ParentProfile toParentProfile(ParentRegisterRequest request, User existingUser) {

        existingUser.setFullName(request.fullName());
        existingUser.setPhone(request.phone());

        ParentProfile parentProfile = new ParentProfile();
        parentProfile.setUser(existingUser);

        if (request.children() != null) {
            request.children().forEach(childRequest -> {
                Child child = childMapper.toChild(childRequest, parentProfile);
                parentProfile.getChildren().add(child);
            });
        }

        return parentProfile;
    }





    public void updateParentFromRequest(ParentProfile existingProfile, ParentUpdateRequest request) {
        if (request == null) return;

        User user = existingProfile.getUser();
        if (user != null) {
            if (request.fullName() != null && !request.fullName().isBlank()) {
                user.setFullName(request.fullName());
            }
            if (request.phone() != null && !request.phone().isBlank()) {
                user.setPhone(request.phone());
            }
            if (request.email() != null && !request.email().isBlank()) {
                user.setEmail(request.email());
            }
        }

        if (request.children() != null) {
            existingProfile.getChildren().clear();

            for (ChildRequest childDto : request.children()) {
                Child child = childMapper.toChild(childDto, existingProfile);
                existingProfile.getChildren().add(child);
            }
        }
    }

    // Response



    public ParentProfileResponse toParentProfileResponse(ParentProfile parentProfile) {
        return new ParentProfileResponse(
                parentProfile.getUser().getId(),
                parentProfile.getUser().getUsername(),

                parentProfile.getId(),
                parentProfile.getUser().getFullName(),
                parentProfile.getUser().getEmail(),
                parentProfile.getUser().getPhone(),

                parentProfile.getChildren().stream()
                        .map(child -> new ChildResponse(
                                child.getId(),
                                child.getFullName(),
                                child.getClassGroup()
                        ))
                        .toList()
        );
    }





    public ParentInfoResponse toParentInfoResponse(ParentProfile parentProfile) {
        return new ParentInfoResponse(
                parentProfile.getId(),

                parentProfile.getUser().getFullName(),
                parentProfile.getUser().getEmail(),
                parentProfile.getUser().getPhone()
        );
    }

}


