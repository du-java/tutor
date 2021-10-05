package com.example.tutor.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ttr_courses")
public class Course implements Model{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate periodStart;
    @Column(nullable = false)
    private LocalDate periodEnd;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;
    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "ttr_courses_group_fk"))
    private Group group;
}
