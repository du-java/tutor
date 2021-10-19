package com.example.tutor.controller;

import com.example.tutor.dto.LessonDto;
import com.example.tutor.facade.LessonFacade;
import com.example.tutor.validation.ChangeLessonDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonFacade lessonFacade;

    @PostMapping
    public ResponseEntity<LessonDto> create(@Valid @RequestBody LessonDto lessonDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonFacade.create(lessonDto));
    }

    @GetMapping
    public ResponseEntity<List<LessonDto>> findAll() {
        return ResponseEntity.ok(lessonFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonFacade.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        lessonFacade.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<LessonDto> update(@Valid @RequestBody LessonDto lessonDto) {
        return ResponseEntity.accepted().body(lessonFacade.update(lessonDto));
    }

    @PostMapping("/lesson")
    public ResponseEntity<LessonDto> changeLesson(@Validated(ChangeLessonDate.class) @RequestBody LessonDto lessonDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lessonFacade.changeLesson(lessonDto));
    }
}
