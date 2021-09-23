package com.example.tutor.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime periodStart;
    @Column(nullable = false)
    private LocalDateTime periodEnd;
//    @Column(nullable = false)
//    private Duration duration;
//    @Column(nullable = false)
//    private DayOfWeek day;
//    @Column(nullable = false)
//    private Time lessonStart;
    @OneToMany
    private List<Lesson> lessons;
}
