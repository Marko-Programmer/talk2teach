package com.marko.talk2teach.mapper;

import com.marko.talk2teach.dto.child.ChildResponse;
import com.marko.talk2teach.dto.parent.ParentInfoResponse;
import com.marko.talk2teach.dto.parent.ParentProfileResponse;
import com.marko.talk2teach.dto.parent.ParentRegisterRequest;
import com.marko.talk2teach.model.Child;
import com.marko.talk2teach.model.ParentProfile;
import com.marko.talk2teach.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ParentMapper {

    private final UserMapper userMapper;
    private final ChildMapper childMapper;

    public ParentProfile toParentProfile(ParentRegisterRequest request) {
        User user = userMapper.toAddBaseInfo(
                request.fullName(),
                request.email(),
                request.phone());

        ParentProfile parentProfile = new ParentProfile();
        parentProfile.setUser(user);

        if (request.children() != null) {
            List<Child> children = request.children().stream()
                    .map(childRequest -> {
                        return childMapper.toChild(childRequest, parentProfile);
                    })
                    .toList();

            parentProfile.setChildren(children);
        }
        return parentProfile;
    }



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


