package com.example.tutor.repositories;

import com.example.tutor.models.Group;
import com.example.tutor.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByGroup(Group group);
}