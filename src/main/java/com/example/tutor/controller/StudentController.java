package com.example.tutor.controller;

import com.example.tutor.dto.CreateStudentDto;
import com.example.tutor.dto.StudentDto;
import com.example.tutor.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentFacade studentFacade;

    @PostMapping
    public ResponseEntity<StudentDto> create(@Valid @RequestBody CreateStudentDto studentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentFacade.create(studentDto));
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> findAll() {
        return ResponseEntity.ok(studentFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(studentFacade.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        studentFacade.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<StudentDto> update(@Valid @RequestBody StudentDto studentDto) {
        return ResponseEntity.accepted().body(studentFacade.update(studentDto));
    }
}
