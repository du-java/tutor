package com.example.tutor.controller;

import com.example.tutor.dto.CourseDto;
import com.example.tutor.dto.CreateCourseRequest;
import com.example.tutor.facade.CourseFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/courses")
@Controller
@RequiredArgsConstructor
public class CourseController {
    private final CourseFacade courseFacadece;
    

    @PostMapping
    public ResponseEntity<CourseDto> create(@Valid @RequestBody CreateCourseRequest createCourseRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseFacadece.create(createCourseRequest));
    }
    @GetMapping
    public ResponseEntity<List<CourseDto>> findAll(){
        return ResponseEntity.ok(courseFacadece.findAll());
    } 
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(courseFacadece.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        courseFacadece.deleteById(id);
        return ResponseEntity.accepted().build();
    }
    @PutMapping
    public ResponseEntity<CourseDto> update(@Valid @RequestBody CourseDto courseDto){
        return ResponseEntity.accepted().body(courseFacadece.update(courseDto));
    }
}
