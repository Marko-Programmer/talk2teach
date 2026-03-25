package com.marko.talk2teach.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "teacher_profiles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private List<String> classRoom;

    @NotEmpty
    private List<String> subjects;

    private String homeroomClass;


    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
}
