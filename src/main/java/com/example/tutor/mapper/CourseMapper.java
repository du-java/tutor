package com.example.tutor.mapper;

import com.example.tutor.dto.CourseDto;
import com.example.tutor.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {LessonsMapper.class, GroupsMapper.class},
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface CourseMapper {

    @Mapping(source = "group.id", target = "groupId")
    CourseDto map(Course entity);

    @Mapping(source = "groupId", target = "group")
    Course map(CourseDto dto);
}
