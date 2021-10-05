package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Builder
public class CreateCourseRequest implements Dto {
    LocalDate start;
    LocalDate end;
    LocalTime lessonStartTime;
    Integer lessonDuration;
    DayOfWeek dayOfWeek;
    Long groupId;
}
