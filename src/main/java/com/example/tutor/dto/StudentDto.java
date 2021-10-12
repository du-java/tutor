package com.example.tutor.dto;

import com.example.tutor.validation.AddLessonsToStudent;
import com.example.tutor.validation.Create;
import com.example.tutor.validation.Update;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class StudentDto implements Dto {
    @NotNull(groups = {Update.class, AddLessonsToStudent.class})
    @Positive(groups = {Update.class, AddLessonsToStudent.class})
    @Null(groups = {Create.class})
    Long id;
    @NotBlank
    String firstname;
    @NotBlank
    String lastname;
    @Email
    @NotNull
    String email;
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    @NotNull
    Long groupId;
    @NotNull
    @Positive
    BigDecimal price;
    @Null(groups = {Create.class})
    @NotNull(groups = {Update.class, AddLessonsToStudent.class})
    List<Long> visitedLessons;
}
