package com.example.tutor.services;

import com.example.tutor.exeptions.NotFoundException;
import com.example.tutor.models.Group;
import com.example.tutor.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group findById(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "group"));
    }

    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }

    public Group update(Group group) {
        return groupRepository.save(group);
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }
}
