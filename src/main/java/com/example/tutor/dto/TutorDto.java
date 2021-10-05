package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value

public class TutorDto implements Dto {
     Long id;
     String firstname;
     String lastname;
     List<Long> group;
}
