package com.example.tutor.services.converter;

import com.example.tutor.dto.CreateGroupRequest;
import com.example.tutor.dto.GroupDto;
import com.example.tutor.models.Course;
import com.example.tutor.models.Group;
import com.example.tutor.models.Student;
import com.example.tutor.services.StudentService;
import com.example.tutor.services.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupConverter implements Converter<Group, GroupDto> {
    private final TutorService tutorService;
    private final StudentService studentService;

    public Group convert(CreateGroupRequest groupDto) {
        return Group.builder()
                .groupName(groupDto.getGroupName())
                .tutor(tutorService.findById(groupDto.getTutorId()))
                .courses(Collections.emptyList())
                .students(Collections.emptyList())
                .build();
    }

    @Override
    public Group convert(GroupDto groupDto) {
        return Group.builder()
                .groupName(groupDto.getGroupName())
                .tutor(tutorService.findById(groupDto.getTutorId()))
                .students(groupDto.getStudents().stream()
                        .map(studentService::findById)
                        .collect(Collectors.toList())
                )
// todo: check
//                .courses(groupDto.getCourses().stream()
//                        .map(courseService::findById)
//                        .collect(Collectors.toList())
//                )
                .build();
    }

    @Override
    public GroupDto convert(Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .tutorId(group.getTutor().getId())
                .students(group.getStudents().stream()
                        .map(Student::getId)
                        .collect(Collectors.toList())
                )
                .courses(group.getCourses().stream()
                        .map(Course::getId)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
