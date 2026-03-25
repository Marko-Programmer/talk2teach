package com.marko.talk2teach.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
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

    ArrayList<?> idd = new ArrayList<>();

    @NotBlank
    private ArrayList<String> classRoom;

    @NotBlank
    private ArrayList<String> subject;

    private String homeroomClass;


    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
}
