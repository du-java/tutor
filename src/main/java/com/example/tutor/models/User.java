package com.example.tutor.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class User implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NaturalId
    private String email;
    @Column(nullable = false)
    private String password;
}
