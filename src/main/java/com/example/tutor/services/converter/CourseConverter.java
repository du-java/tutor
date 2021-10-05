package com.example.tutor.services.converter;

import com.example.tutor.dto.CourseDto;
import com.example.tutor.models.Course;
import com.example.tutor.models.Lesson;
import com.example.tutor.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseConverter implements Converter<Course, CourseDto> {

    private final LessonService lessonService;

    @Override
    public Course convert(CourseDto courseDto) {
        return Course.builder()
                .group(courseDto.getGroup())
                .id(courseDto.getId())
                .periodStart(courseDto.getPeriodStart())
                .periodEnd(courseDto.getPeriodEnd())
                .lessons(courseDto.getLessons().stream()
                        .map(lessonService::findById)
                        .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public CourseDto convert(Course course) {
        return CourseDto.builder()
                .group(course.getGroup())
                .id(course.getId())
                .periodStart(course.getPeriodStart())
                .periodEnd(course.getPeriodEnd())
                .lessons(course.getLessons().stream()
                        .map(Lesson::getId)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
