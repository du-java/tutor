package com.example.tutor.mapper;

import com.example.tutor.models.Course;
import com.example.tutor.services.CourseService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CoursesMapper {

    @Autowired
    private CourseService courseService;

    public Course map(Long id) {
        return courseService.findById(id);
    }

    public Long map(Course entity) {
        return entity.getId();
    }

}
