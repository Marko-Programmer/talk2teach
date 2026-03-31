package com.marko.talk2teach.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admin_profiles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
}