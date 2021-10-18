package com.example.tutor.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;

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
