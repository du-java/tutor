package com.example.tutor.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ttr_students")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Student extends User {

    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "ttr_students_group_fk"))
    private Group group;

    @Column
    private BigDecimal price;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "ttr_student_lessons",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "lesson_id")}
    )
    @ToString.Exclude
    private List<Lesson> visitedLessons;
}
