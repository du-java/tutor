package com.example.tutor.dto;

import com.example.tutor.validation.Create;
import com.example.tutor.validation.Update;
import lombok.Builder;
import lombok.Value;

import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.*;
import java.util.List;

@Value
@Builder
public class TutorDto implements Dto {
    @NotNull(groups = {Update.class})
    @Positive(groups = {Update.class})
    @Null(groups = {Create.class})
    Long id;
    @NotNull
    String firstname;
    @NotNull
    String lastname;
    @NotNull(groups = {Update.class})
    List<Long> groups;
    @Email
    @NotNull
    String email;
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
}
