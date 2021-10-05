package com.example.tutor.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ttr_tutors")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Tutor extends User {
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;

    @OneToMany(mappedBy = "tutor")
    private List<Group> group;
}
