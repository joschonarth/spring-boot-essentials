package br.com.joschonarth.spring_boot_essentials.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "exercise")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "muscle_group", nullable = false)
    private String muscleGroup;

    @Column()
    private LocalDate equipment;

    @Column(name = "difficulty_level")
    private LocalDate difficultyLevel;
}
