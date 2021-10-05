package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GroupDto implements Dto {
    String groupName;
    List<Long> students;
    List<Long> courses;
    Long tutor;
    Long id;
}
