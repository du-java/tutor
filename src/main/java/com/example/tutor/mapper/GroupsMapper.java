package com.example.tutor.mapper;

import com.example.tutor.models.Group;
import com.example.tutor.services.GroupService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class GroupsMapper {

    @Autowired
    private GroupService groupService;

    public Group map(Long id) {
        return groupService.findById(id);
    }
    public Long map(Group entity) {
        return entity.getId();
    }

}
