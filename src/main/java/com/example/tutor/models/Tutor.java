package com.example.tutor.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
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

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Group> groups;
}
