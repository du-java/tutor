package com.example.tutor.controller;

import com.example.tutor.dto.GroupDto;
import com.example.tutor.facade.GroupFacade;
import com.example.tutor.validation.Create;
import com.example.tutor.validation.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupFacade groupFacade;

    @PostMapping
    public ResponseEntity<GroupDto> create(@Validated(Create.class) @RequestBody GroupDto groupDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupFacade.create(groupDto));
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> findAll() {
        return ResponseEntity.ok(groupFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(groupFacade.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        groupFacade.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<GroupDto> update(@Validated(Update.class) @RequestBody GroupDto groupDto) {
        return ResponseEntity.accepted().body(groupFacade.update(groupDto));
    }
}
