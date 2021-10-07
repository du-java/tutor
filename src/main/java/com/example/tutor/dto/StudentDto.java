package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    String lastname;
    @NotBlank
    String firstname;
    @NotNull
    Long groupId;
    BigDecimal price;
    List<Long> visitedLessons;
    @Email
    @NotNull
    String email;
    @NotBlank
    @JsonIgnore
    String password;
}
