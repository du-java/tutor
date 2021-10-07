package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class CreateTutorDto implements Dto {
    @NotNull
    String firstname;
    @NotNull
    String lastname;
    @Email
    @NotNull
    String email;
    @NotBlank
    String password;
}
