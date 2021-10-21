package com.example.tutor.mapper;

import com.example.tutor.dto.LessonDto;
import com.example.tutor.models.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {GroupsMapper.class, StudentsMapper.class, CoursesMapper.class},
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface LessonMapper {

    @Mapping(source = "course.id", target = "courseId")
    LessonDto map(Lesson entity);

    @Mapping(source = "courseId", target = "course")
    Lesson map(LessonDto dto);
}
