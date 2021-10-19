package com.example.tutor.services.converter;

import com.example.tutor.dto.GroupDto;
import com.example.tutor.models.Course;
import com.example.tutor.models.Group;
import com.example.tutor.models.Student;
import com.example.tutor.services.CourseService;
import com.example.tutor.services.utils.ListFiller;
import com.example.tutor.services.StudentService;
import com.example.tutor.services.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupConverter implements Converter<Group, GroupDto> {
    private final TutorService tutorService;
    private final StudentService studentService;
    private final CourseService courseService;
    private final ListFiller listFiller;

    @Override
    public Group convert(GroupDto groupDto) {
        final List<Long> courses = listFiller.ifNullGetEmptyList(groupDto.getCourses());
        final List<Long> students = listFiller.ifNullGetEmptyList(groupDto.getStudents());

        return Group.builder()
                .groupName(groupDto.getGroupName())
                .tutor(tutorService.findById(groupDto.getTutorId()))
                .students(students.stream()
                        .map(studentService::findById)
                        .collect(Collectors.toList())
                )
                .courses(courses.stream()
                        .map(courseService::findById)
                        .collect(Collectors.toSet())
                )
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
