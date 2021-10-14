package com.example.tutor.controller;

import com.example.tutor.dto.TutorDto;
import com.example.tutor.facade.TutorFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tutors")
public class TutorController {

    private final TutorFacade tutorFacade;

    @PostMapping
    public ResponseEntity<TutorDto> create(@Valid @RequestBody TutorDto tutorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tutorFacade.create(tutorDto));
    }

    @GetMapping
    public ResponseEntity<List<TutorDto>> findAll() {
        return ResponseEntity.ok(tutorFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tutorFacade.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        tutorFacade.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<TutorDto> update(@Valid @RequestBody TutorDto tutorDto) {
        return ResponseEntity.accepted().body(tutorFacade.update(tutorDto));
    }
}
