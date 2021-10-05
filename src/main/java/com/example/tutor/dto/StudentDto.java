package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class StudentDto implements Dto {
    Long id;
    String lastname;
    String firstname;
    Long group;
    BigDecimal price;
    List<Long> visitedLessons;
}
