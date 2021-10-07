package com.example.tutor.facade;

import com.example.tutor.dto.StudentDto;
import com.example.tutor.services.StudentService;
import com.example.tutor.services.converter.StudentConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentFacade {

    private final StudentService studentService;
    private final StudentConverter studentConverter;

    public StudentDto create(StudentDto studentDto) {
        return studentConverter.convert(studentService.save(studentConverter.convert(studentDto)));
    }

    public List<StudentDto> findAll() {
        return studentService.findAll().stream()
                .map(studentConverter::convert)
                .collect(Collectors.toList());
    }

    public StudentDto findById(Long id) {
        return studentConverter.convert(studentService.findById(id));
    }

    public void deleteById(Long id) {
        studentService.deleteById(id);
    }

    public StudentDto update(StudentDto studentDto) {
        return create(studentDto);
    }
}
