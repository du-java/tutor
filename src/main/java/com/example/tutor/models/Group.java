package com.example.tutor.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ttr_groups")
public class Group implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String groupName;

    @ToString.Exclude
    @OneToMany(mappedBy = "group")
    private List<Student> students;

    @ToString.Exclude
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Course> courses;

    @ManyToOne
    @JoinColumn(name = "tutor_id", foreignKey = @ForeignKey(name = "ttr_groups_tutor_fk"), nullable = false)
    private Tutor tutor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Group group = (Group) o;
        return id != null && Objects.equals(id, group.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
