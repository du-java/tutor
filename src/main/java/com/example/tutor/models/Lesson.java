package com.example.tutor.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ttr_lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime start;
    @Column(nullable = false)
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "ttr_lessons_course_fk"))
    private Course course;

    @ManyToMany
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "ttr_lessons_student_fk"))
    private List<Student> students;
}
