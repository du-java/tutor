package com.example.tutor.dto;

import com.example.tutor.models.Course;
import com.example.tutor.validation.ChangeLessonDate;
import com.example.tutor.validation.Create;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class LessonDto implements Dto {
    @NotNull(groups = {ChangeLessonDate.class})
    @Positive(groups = {ChangeLessonDate.class})
    Long id;
    @NotNull(groups = {ChangeLessonDate.class})
    LocalDateTime start;
    @NotNull
    Duration duration;
    @NotNull
    @Positive
    Long courseId;
    List<Long> students;
}
