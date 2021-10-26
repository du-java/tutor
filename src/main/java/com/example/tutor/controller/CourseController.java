package com.example.tutor.controller;

import com.example.tutor.dto.CourseDto;
import com.example.tutor.request.CreateCourseRequest;
import com.example.tutor.facade.CourseFacade;
import com.example.tutor.validation.ChangeCourseDate;
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
@RequestMapping("/courses")
public class CourseController {

    private final CourseFacade courseFacade;

    @PostMapping
    public ResponseEntity<CourseDto> create( @RequestBody CreateCourseRequest createCourseRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseFacade.create(createCourseRequest));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> findAll() {
        return ResponseEntity.ok(courseFacade.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseFacade.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        courseFacade.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    public ResponseEntity<CourseDto> update(@Valid @RequestBody CourseDto courseDto) {
        return ResponseEntity.accepted().body(courseFacade.update(courseDto));
    }

    @PostMapping("/course")
    public ResponseEntity<CourseDto> changeCourseDate(@Validated(ChangeCourseDate.class) @RequestBody CourseDto courseDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseFacade.changeCourseDate(courseDto));
    }
}
