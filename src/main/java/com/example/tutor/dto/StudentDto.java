package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class StudentDto implements Dto {
    @NotNull
    @Positive
    Long id;
    @NotNull
    String lastname;
    @NotNull
    String firstname;
    Long groupId;
    BigDecimal price;
    List<Long> visitedLessons;
}
