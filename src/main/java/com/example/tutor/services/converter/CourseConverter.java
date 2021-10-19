package com.example.tutor.services.converter;

import com.example.tutor.dto.CourseDto;
import com.example.tutor.models.Course;
import com.example.tutor.models.Lesson;
import com.example.tutor.request.CreateCourseRequest;
import com.example.tutor.services.GroupService;
import com.example.tutor.services.LessonService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseConverter implements Converter<Course, CourseDto> {

    private final LessonService lessonService;
    private final GroupService groupService;

    public Course convert(CreateCourseRequest createCourseRequest) {
        final Course course = Course.builder()
                .periodStart(createCourseRequest.getStart())
                .periodEnd(createCourseRequest.getEnd())
                .group(groupService.findById(createCourseRequest.getGroupId()))
                .build();
        final List<Lesson> lessons = lessonService.createLessons(createCourseRequest, course.getGroup().getTutor()).stream()
                .peek(l -> l.setCourse(course))
                .collect(Collectors.toList());
        course.setLessons(lessons);
        return course;
    }

    @Override
    public Course convert(CourseDto courseDto) {
        return Course.builder()
                .group(groupService.findById(courseDto.getId()))
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
                .groupId(course.getGroup().getId())
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
