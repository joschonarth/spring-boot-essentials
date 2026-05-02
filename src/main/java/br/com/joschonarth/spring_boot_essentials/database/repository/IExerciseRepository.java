package br.com.joschonarth.spring_boot_essentials.database.repository;

import br.com.joschonarth.spring_boot_essentials.database.model.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IExerciseRepository extends JpaRepository<ExerciseEntity, UUID> {

    List<ExerciseEntity> findAllByMuscleGroup(String muscleGroup);
}
