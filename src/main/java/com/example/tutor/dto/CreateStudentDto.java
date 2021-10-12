package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Value
@Builder
public class CreateStudentDto implements Dto {
    @NotNull
    String firstname;
    @NotNull
    String lastname;
    @Email
    @NotNull
    String email;
    @NotBlank
    String password;

    @NotNull
    @Positive
    Long groupId;
    @NotNull
    @Positive
    BigDecimal price;
}
