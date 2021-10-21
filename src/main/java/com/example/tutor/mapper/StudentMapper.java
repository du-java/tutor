package com.example.tutor.mapper;

import com.example.tutor.dto.StudentDto;
import com.example.tutor.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {LessonsMapper.class, GroupsMapper.class},
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface StudentMapper {

    @Mapping(source = "group.id", target = "groupId")
    StudentDto map(Student entity);

    @Mapping(source = "groupId", target = "group")
    Student map(StudentDto dto);
}
