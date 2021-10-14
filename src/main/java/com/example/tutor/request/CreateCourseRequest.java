package com.example.tutor.request;

import com.example.tutor.dto.Dto;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Builder
public class CreateCourseRequest implements Dto {
    @NotNull
    LocalDate start;
    @NotNull
    LocalDate end;
    @NotNull
    LocalTime lessonStartTime;
    @NotNull
    @Positive
    Integer lessonDuration;
    @NotBlank
    String dayOfWeek;
    @NotNull
    @Positive
    Long groupId;
}
