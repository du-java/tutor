package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Value
@Builder
public class CreateGroupRequest implements Dto {
    @NotBlank
    @Size(min = 3, max = 100)
    String groupName;
    @NotNull
    @Positive
    Long tutorId;
}
