package com.example.tutor.services.converter;

import com.example.tutor.dto.StudentDto;
import com.example.tutor.models.Lesson;
import com.example.tutor.models.Student;
import com.example.tutor.services.GroupService;
import com.example.tutor.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentConverter implements Converter<Student, StudentDto> {

    private final LessonService lessonService;
    private final GroupService groupService;

    @Override
    public Student convert(StudentDto studentDto) {
        return Student.builder()
                .id(studentDto.getId())
                .firstname(studentDto.getFirstname())
                .lastname(studentDto.getLastname())
                .group(groupService.findById(studentDto.getId()))
                .price(studentDto.getPrice())
                .visitedLessons(studentDto.getVisitedLessons().stream()
                        .map(lessonService::findById)
                        .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public StudentDto convert(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .firstname(student.getFirstname())
                .lastname(student.getLastname())
                .group(student.getGroup().getId())
                .price(student.getPrice())
                .visitedLessons(student.getVisitedLessons().stream()
                        .map(Lesson::getId)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
