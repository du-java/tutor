package com.example.tutor.facade;

import com.example.tutor.dto.GroupDto;
import com.example.tutor.mapper.GroupMapper;
import com.example.tutor.models.Group;
import com.example.tutor.models.Student;
import com.example.tutor.services.GroupService;
import com.example.tutor.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupFacade {

    private final GroupService groupService;
    private final GroupMapper groupMapper;
    private final StudentService studentService;

    public GroupDto create(GroupDto groupDto) {
        return groupMapper.map(groupService.save(groupMapper.map(groupDto)));
    }

    public List<GroupDto> findAll() {
        return groupService.findAll().stream()
                .map(groupMapper::map)
                .collect(Collectors.toList());
    }

    public GroupDto findById(Long id) {
        return groupMapper.map(groupService.findById(id));
    }

    public void deleteById(Long id) {
        groupService.deleteById(id);
    }

    public GroupDto update(GroupDto groupDto) {
        return groupMapper.map(groupService.save(groupMapper.map(groupDto)));
    }

    public GroupDto addStudents(GroupDto groupDto) {
        List<Long> studentsId = groupDto.getStudents();
        Group group = groupService.findById(groupDto.getId());
        List<Student> students = studentsId.stream()
                .map(studentService::findById)
                .collect(Collectors.toList());
        group.getStudents().addAll(students);
        return groupMapper.map(groupService.save(group));
    }
}
