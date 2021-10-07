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
public class Lesson implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime start;
    @Column(nullable = false)
    private Duration duration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "ttr_lessons_course_fk"), nullable = false)
    private Course course;

    @ManyToMany(mappedBy = "visitedLessons", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Student> students;
}
