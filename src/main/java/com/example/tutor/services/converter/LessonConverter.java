package com.example.tutor.services.converter;

import com.example.tutor.dto.LessonDto;
import com.example.tutor.models.Lesson;
import com.example.tutor.models.Student;
import com.example.tutor.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonConverter implements Converter<Lesson, LessonDto> {

    private final StudentService studentService;

    @Override
    public Lesson convert(LessonDto lessonDto) {
        return Lesson.builder()
                .id(lessonDto.getId())
                .start(lessonDto.getStart())
                .duration(lessonDto.getDuration())
                .students(lessonDto.getStudents().stream()
                        .map(studentService::findById)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public LessonDto convert(Lesson lesson) {
        return LessonDto.builder()
                .id(lesson.getId())
                .start(lesson.getStart())
                .duration(lesson.getDuration())
                .students(lesson.getStudents().stream()
                        .map(Student::getId)
                        .collect(Collectors.toList())
                )
                .courseId(lesson.getCourse().getId())
                .build();

    }
}
