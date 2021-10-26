package com.example.tutor.services.converter;

import com.example.tutor.dto.CourseDto;
import com.example.tutor.models.Course;
import com.example.tutor.models.Lesson;
import com.example.tutor.request.CreateCourseRequest;
import com.example.tutor.services.GroupService;
import com.example.tutor.services.LessonService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseConverter implements Converter<Course, CourseDto> {

    private final LessonService lessonService;
    private final GroupService groupService;

    public CreateCourseRequest getCourseRequest(Course course){
return CreateCourseRequest.builder()
        .periodStart(course.getPeriodStart())
        .periodEnd(course.getPeriodEnd())
        .lessonStartTime(course.getLessons().get(0).getStart().toLocalTime())
        .dayOfWeek(course.getLessons().get(0).getStart().getDayOfWeek().toString())
        .lessonDuration(course.getLessons().get(0).getDuration().toMinutesPart())
        .groupId(course.getGroup().getId())
        .build();
    }
    public Course convert(CreateCourseRequest createCourseRequest) {
        return Course.builder()
                .periodStart(createCourseRequest.getPeriodStart())
                .periodEnd(createCourseRequest.getPeriodEnd())
                .group(groupService.findById(createCourseRequest.getGroupId()))
                .build();
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
