package com.example.tutor.mapper;

import com.example.tutor.models.Student;
import com.example.tutor.services.StudentService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class StudentsMapper {

    @Autowired
    private StudentService studentService;

    public Long map(Student entity) {
        return entity.getId();
    }

    public Student map(Long id) {
        return studentService.findById(id);
    }

}
