package com.example.tutor.dto;

import com.example.tutor.models.Group;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Builder
@Value
public class CourseDto implements Dto {
    Long id;
    LocalDate periodStart;
    LocalDate periodEnd;
    List<Long> lessons;
    Group group;
}
