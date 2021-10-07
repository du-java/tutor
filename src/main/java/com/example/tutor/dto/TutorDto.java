package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Value
@Builder
public class TutorDto implements Dto {
    @NotNull
    @Positive
    Long id;
    @NotNull
    String firstname;
    @NotNull
    String lastname;
    List<Long> groupId;
}
