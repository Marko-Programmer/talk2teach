package com.marko.talk2teach.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "parent_profiles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> children;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
}
