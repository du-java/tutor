package com.example.tutor.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
@Builder
@Value
public class GroupDto implements Dto {
     String groupName;
     List<Long> students;
     List<Long> courses;
     Long tutor;
      Long Id;
}
