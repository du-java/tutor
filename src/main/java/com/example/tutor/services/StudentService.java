package com.example.tutor.services;

import com.example.tutor.exeptions.NotFoundException;
import com.example.tutor.models.Group;
import com.example.tutor.models.Student;
import com.example.tutor.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "student"));
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByGroup(Group group) {
        return studentRepository.findByGroup(group);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "student"));
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

}
