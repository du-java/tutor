package com.example.tutor.controller;

import com.example.tutor.request.PriceByPeriod;
import com.example.tutor.dto.StudentDto;
import com.example.tutor.facade.StudentFacade;
import com.example.tutor.respons.PayByLessonsResponse;
import com.example.tutor.validation.AddLessonsToStudent;
import com.example.tutor.validation.Create;
import com.example.tutor.validation.Update;
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
@RequestMapping("/students")
public class StudentController {

    private final StudentFacade studentFacade;

    @PostMapping
    public ResponseEntity<StudentDto> create(@Validated(Create.class) @RequestBody StudentDto studentDto) {
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
    public ResponseEntity<StudentDto> update(@Validated(Update.class) @RequestBody StudentDto studentDto) {
        return ResponseEntity.accepted().body(studentFacade.update(studentDto));
    }

    @PutMapping("/addLessons")
    public ResponseEntity<StudentDto> updateVisitedLessons(@Validated(AddLessonsToStudent.class) @RequestBody StudentDto studentDto) {
        return ResponseEntity.accepted().body(studentFacade.updateVisitedLessons(studentDto));
    }

    @PutMapping("/{studentId}/lesson/{lessonId}")
    public ResponseEntity<StudentDto> setVisitedLesson(@PathVariable Long studentId, @PathVariable Long lessonId) {
        return ResponseEntity.accepted().body(studentFacade.setVisitedLesson(studentId, lessonId));
    }

    @PostMapping("/getPriceByLessons")
    public ResponseEntity<PayByLessonsResponse> getPriceByLessons(@Valid @RequestBody PriceByPeriod priceByPeriod) {
        return ResponseEntity.ok(studentFacade.getPriceByLessons(priceByPeriod));
    }
}
