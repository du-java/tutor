package com.example.tutor.dto;

import com.example.tutor.models.Course;
import lombok.Builder;
import lombok.Value;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Value
public class LessonDto implements Dto {
    Long id;
    LocalDateTime start;
    Duration duration;
    Course course;
    List<Long> students;

}
