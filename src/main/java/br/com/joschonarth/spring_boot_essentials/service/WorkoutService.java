package br.com.joschonarth.spring_boot_essentials.service;

import br.com.joschonarth.spring_boot_essentials.database.model.ExerciseEntity;
import br.com.joschonarth.spring_boot_essentials.database.model.StudentEntity;
import br.com.joschonarth.spring_boot_essentials.database.model.WorkoutEntity;
import br.com.joschonarth.spring_boot_essentials.database.repository.IExerciseRepository;
import br.com.joschonarth.spring_boot_essentials.database.repository.IStudentRepository;
import br.com.joschonarth.spring_boot_essentials.database.repository.IWorkoutRepository;
import br.com.joschonarth.spring_boot_essentials.dto.WorkoutDTO;
import br.com.joschonarth.spring_boot_essentials.exception.BadRequestException;
import br.com.joschonarth.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final IStudentRepository studentRepository;
    private final IExerciseRepository exerciseRepository;
    private final IWorkoutRepository workoutRepository;

    public void createWorkout(WorkoutDTO workoutDTO) throws NotFoundException, BadRequestException {
        Set<ExerciseEntity> exercises = new HashSet<>();

        StudentEntity student = studentRepository.findById(workoutDTO.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student not found"));

        WorkoutEntity workout = workoutRepository.findByNameAndStudentId(workoutDTO.getName(), workoutDTO.getStudentId())
                .orElse(null);

        if (workout != null) {
            throw new BadRequestException("Already exists a workout with this name for this student");
        }

        for (UUID exerciseId : workoutDTO.getExerciseId()) {
            ExerciseEntity exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new NotFoundException(String.format("Exercise %s not found", exerciseId)));

            exercises.add(exercise);
        }

        workout = WorkoutEntity.builder()
                .name(workoutDTO.getName())
                .objective(workoutDTO.getObjective())
                .student(student)
                .exercises(exercises)
                .build();

        workoutRepository.save(workout);
    }
}
