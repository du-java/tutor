package com.example.tutor.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Student extends User {

    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @ManyToOne
    private Group group;
    @Column(nullable = false)
    private BigDecimal price;
    @OneToMany
    private List<Lesson> visitedLessons;
}
