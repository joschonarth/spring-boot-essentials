package br.com.joschonarth.spring_boot_essentials.database.repository;

import br.com.joschonarth.spring_boot_essentials.database.model.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWorkoutRepository extends JpaRepository<WorkoutEntity, UUID> {

    Optional<WorkoutEntity> findByNameAndStudentId(String name, UUID studentId);

    Optional<WorkoutEntity> findByIdAndStudentId(UUID workoutId, UUID studentId);

    List<WorkoutEntity> findAllByStudentId(UUID studentId);
}
