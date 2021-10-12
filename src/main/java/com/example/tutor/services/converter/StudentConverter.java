package com.example.tutor.services.converter;

import com.example.tutor.dto.CreateStudentDto;
import com.example.tutor.dto.StudentDto;
import com.example.tutor.models.Lesson;
import com.example.tutor.models.Student;
import com.example.tutor.services.GroupService;
import com.example.tutor.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentConverter implements Converter<Student, StudentDto> {

    private final LessonService lessonService;
    private final GroupService groupService;

    public Student convert (CreateStudentDto createStudentDto){
        return Student.builder()
                .firstname(createStudentDto.getFirstname())
                .lastname(createStudentDto.getLastname())
                .email(createStudentDto.getEmail())
                .password(createStudentDto.getPassword())
                .group((groupService.findById(createStudentDto.getGroupId())))
                .visitedLessons(Collections.emptyList())
                .price(createStudentDto.getPrice())
                .build();
    }

    @Override
    public Student convert(StudentDto studentDto) {
        return Student.builder()
                .id(studentDto.getId())
                .firstname(studentDto.getFirstname())
                .lastname(studentDto.getLastname())
                .email(studentDto.getEmail())
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
