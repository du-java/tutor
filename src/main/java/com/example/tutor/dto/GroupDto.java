package com.example.tutor.dto;

import com.example.tutor.validation.AddStudentToGroup;
import com.example.tutor.validation.Create;
import com.example.tutor.validation.Update;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.*;
import java.util.List;

@Value
@Builder
public class GroupDto implements Dto {
    @NotNull(groups = {Update.class, AddStudentToGroup.class})
    @Positive(groups = {Update.class, AddStudentToGroup.class})
    @Null(groups = {Create.class})
    Long id;
    @NotBlank
    @Size(min = 3, max = 100)
    String groupName;
    @NotNull
    @Positive
    Long tutorId;
    @Null(groups = {Create.class})
    @NotNull(groups = {Update.class})
    List<Long> courses;
    @Null(groups = {Create.class})
    @NotNull(groups = {Update.class, AddStudentToGroup.class})
    List<Long> students;
}
