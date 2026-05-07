package br.com.joschonarth.spring_boot_essentials.config;

import br.com.joschonarth.spring_boot_essentials.database.model.ExerciseEntity;
import br.com.joschonarth.spring_boot_essentials.database.model.RolesEntity;
import br.com.joschonarth.spring_boot_essentials.database.model.StudentEntity;
import br.com.joschonarth.spring_boot_essentials.database.repository.IExerciseRepository;
import br.com.joschonarth.spring_boot_essentials.database.repository.IRolesRepository;
import br.com.joschonarth.spring_boot_essentials.database.repository.IStudentRepository;
import br.com.joschonarth.spring_boot_essentials.enums.DifficultyLevel;
import br.com.joschonarth.spring_boot_essentials.enums.RoleTypeEnum;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final IStudentRepository studentRepository;
    private final IRolesRepository rolesRepository;
    private final IExerciseRepository exerciseRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String @NonNull ... args) {

        if (studentRepository.count() > 0) return;

        RolesEntity adminRole = rolesRepository.findByName(RoleTypeEnum.ROLE_ADMIN.name())
                .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                        .name(RoleTypeEnum.ROLE_ADMIN.name())
                        .build()));

        RolesEntity studentRole = rolesRepository.findByName(RoleTypeEnum.ROLE_STUDENT.name())
                .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                        .name(RoleTypeEnum.ROLE_STUDENT.name())
                        .build()));

        studentRepository.saveAll(List.of(
                StudentEntity.builder()
                        .name("Admin")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("admin123"))
                        .birthDate(LocalDate.of(1990, 1, 1))
                        .roles(Set.of(adminRole))
                        .build(),
                StudentEntity.builder()
                        .name("John Doe")
                        .email("johndoe@example.com")
                        .password(passwordEncoder.encode("john123"))
                        .birthDate(LocalDate.of(2000, 6, 15))
                        .roles(Set.of(studentRole))
                        .build()
        ));

        exerciseRepository.saveAll(List.of(
                ExerciseEntity.builder().name("Bench Press").muscleGroup("CHEST").equipment("Barbell").difficultyLevel(DifficultyLevel.INTERMEDIATE).build(),
                ExerciseEntity.builder().name("Pull Up").muscleGroup("BACK").equipment("Bar").difficultyLevel(DifficultyLevel.ADVANCED).build(),
                ExerciseEntity.builder().name("Squat").muscleGroup("LEGS").equipment("Barbell").difficultyLevel(DifficultyLevel.INTERMEDIATE).build(),
                ExerciseEntity.builder().name("Shoulder Press").muscleGroup("SHOULDERS").equipment("Dumbbell").difficultyLevel(DifficultyLevel.BEGINNER).build(),
                ExerciseEntity.builder().name("Bicep Curl").muscleGroup("ARMS").equipment("Dumbbell").difficultyLevel(DifficultyLevel.BEGINNER).build()
        ));
    }
}