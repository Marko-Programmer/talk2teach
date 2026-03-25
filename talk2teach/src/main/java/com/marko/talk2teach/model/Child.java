package com.marko.talk2teach.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "childrens")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String fullName;

    @NotBlank
    private String classGroup;


    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ParentProfile parent;
}
