package com.example.tutor.models;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

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
    private List<Group> groups;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Tutor tutor = (Tutor) o;
        return getId() != null && Objects.equals(getId(), tutor.getId());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
