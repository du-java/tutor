package com.example.tutor.services.converter;

import com.example.tutor.dto.StudentDto;
import com.example.tutor.models.Lesson;
import com.example.tutor.models.Student;
import com.example.tutor.services.GroupService;
import com.example.tutor.services.LessonService;
import com.example.tutor.services.ListFiller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentConverter implements Converter<Student, StudentDto> {

    private final LessonService lessonService;
    private final GroupService groupService;
    private final ListFiller listFiller;

    @Override
    public Student convert(StudentDto studentDto) {
        final List<Long> visitedLessons = listFiller.ifNullGetEmptyList(studentDto.getVisitedLessons());

        return Student.builder()
                .id(studentDto.getId())
                .firstname(studentDto.getFirstname())
                .lastname(studentDto.getLastname())
                .email(studentDto.getEmail())
                .group(groupService.findById(studentDto.getGroupId()))
                .price(studentDto.getPrice())
                .password(studentDto.getPassword())
                .visitedLessons(visitedLessons.stream()
                        .map(lessonService::findById)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    @Override
    public StudentDto convert(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .firstname(student.getFirstname())
                .email(student.getEmail())
                .lastname(student.getLastname())
                .groupId(student.getGroup().getId())
                .price(student.getPrice())
                .visitedLessons(student.getVisitedLessons().stream()
                        .map(Lesson::getId)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
