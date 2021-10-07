package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @Email
    @NotNull
    String email;
    @NotBlank
    @JsonIgnore
    String password;
}
