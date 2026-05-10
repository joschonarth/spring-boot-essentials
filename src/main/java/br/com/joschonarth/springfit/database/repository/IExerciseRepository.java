package br.com.joschonarth.springfit.database.repository;

import br.com.joschonarth.springfit.database.model.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IExerciseRepository extends JpaRepository<ExerciseEntity, UUID> {

    List<ExerciseEntity> findAllByMuscleGroup(String muscleGroup);

    @Query(value = """
        SELECT e
        FROM ExerciseEntity e
        WHERE UPPER(e.muscleGroup) = UPPER(:muscleGroup)
    """)
    List<ExerciseEntity> findAllByMuscleGroupJpql(@Param("muscleGroup") String muscleGroup);

    @NativeQuery(value = """
        SELECT e
        FROM exercise e
        WHERE UPPER(e.muscle_group) = UPPER(:muscleGroup)
    """)
    List<ExerciseEntity> findAllByMuscleGroupNative(@Param("muscleGroup") String muscleGroup);

    List<ExerciseEntity> findByMuscleGroupIgnoreCase(String muscleGroup);
}
