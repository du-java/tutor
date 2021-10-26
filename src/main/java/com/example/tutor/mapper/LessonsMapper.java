package com.example.tutor.mapper;

import com.example.tutor.models.Lesson;
import com.example.tutor.services.LessonService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LessonsMapper {

    @Autowired
    private LessonService lessonService;

    public Long map(Lesson entity) {
        return entity.getId();
    }

    public Lesson map(Long id) {
        return lessonService.findById(id);
    }
}
