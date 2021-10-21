package com.example.tutor.mapper;

import com.example.tutor.dto.GroupDto;
import com.example.tutor.models.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {TutorsMapper.class, StudentsMapper.class, CoursesMapper.class},
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface GroupMapper {

    @Mapping(source = "tutor.id", target = "tutorId")
    GroupDto map(Group entity);

    @Mapping(source = "tutorId", target = "tutor")
    Group map(GroupDto dto);
}
