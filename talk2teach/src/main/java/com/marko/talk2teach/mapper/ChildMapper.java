package com.marko.talk2teach.mapper;

import com.marko.talk2teach.dto.child.ChildResponse;
import com.marko.talk2teach.dto.child.ChildRequest;
import com.marko.talk2teach.model.Child;
import com.marko.talk2teach.model.ParentProfile;
import org.springframework.stereotype.Component;

@Component
public class ChildMapper {

    public Child toChild(ChildRequest childRequest, ParentProfile parent) {
        if (childRequest == null && parent == null) return null;

        Child child = new Child();
        child.setFullName(childRequest.fullName());
        child.setClassGroup(childRequest.classGroup());
        child.setParent(parent);

        return child;
    }

    public ChildResponse toChildResponse(Child child){
        return new ChildResponse(
                child.getId(),
                child.getFullName(),
                child.getClassGroup()
        );
    }

}