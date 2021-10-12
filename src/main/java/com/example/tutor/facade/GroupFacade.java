package com.example.tutor.facade;

import com.example.tutor.dto.GroupDto;
import com.example.tutor.services.GroupService;
import com.example.tutor.services.converter.GroupConverter;
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
    private final GroupConverter groupConverter;

    public GroupDto create(GroupDto groupDto) {
        return groupConverter.convert(groupService.save(groupConverter.convert(groupDto)));
    }

    public List<GroupDto> findAll() {
        return groupService.findAll().stream()
                .map(groupConverter::convert)
                .collect(Collectors.toList());
    }

    public GroupDto findById(Long id) {
        return groupConverter.convert(groupService.findById(id));
    }

    public void deleteById(Long id) {
        groupService.deleteById(id);
    }

    public GroupDto update(GroupDto groupDto) {
        return groupConverter.convert(groupService.save(groupConverter.convert(groupDto)));
    }
}
