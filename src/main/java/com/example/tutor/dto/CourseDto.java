package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class CourseDto implements Dto {
    @NotNull
    @Positive
    Long id;
    @NotNull
    LocalDate periodStart;
    @NotNull
    LocalDate periodEnd;
    @NotNull
    List<Long> lessons;
    @NotNull
    @Positive
    Long groupId;
}
