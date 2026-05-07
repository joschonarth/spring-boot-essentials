package br.com.joschonarth.spring_boot_essentials.service;

import br.com.joschonarth.spring_boot_essentials.database.model.ExerciseEntity;
import br.com.joschonarth.spring_boot_essentials.database.repository.IExerciseRepository;
import br.com.joschonarth.spring_boot_essentials.dto.ExerciseDTO;
import br.com.joschonarth.spring_boot_essentials.dto.ExerciseResponseDTO;
import br.com.joschonarth.spring_boot_essentials.enums.DifficultyLevel;
import br.com.joschonarth.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final IExerciseRepository exerciseRepository;

    public List<ExerciseEntity> findAll() {
        return exerciseRepository.findAll();
    }

    public void save(ExerciseDTO exerciseDTO) {
        ExerciseEntity exercise = ExerciseEntity.builder()
                .name(exerciseDTO.getName())
                .muscleGroup(exerciseDTO.getMuscleGroup())
                .equipment(exerciseDTO.getEquipment())
                .difficultyLevel(
                        exerciseDTO.getDifficultyLevel() != null
                            ? exerciseDTO.getDifficultyLevel()
                            : DifficultyLevel.BEGINNER
                )
                .build();

        exerciseRepository.save(exercise);
    }

    public List<ExerciseEntity> getExerciseByMuscleGroup(String muscleGroup) {
        return exerciseRepository.findAllByMuscleGroup(muscleGroup);
    }

    public ExerciseResponseDTO getExerciseById(UUID exerciseId) throws NotFoundException {
        ExerciseEntity exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NotFoundException("Exercise not found"));

        return ExerciseResponseDTO.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .muscleGroup(exercise.getMuscleGroup())
                .equipment(exercise.getEquipment())
                .difficultyLevel(exercise.getDifficultyLevel())
                .build();
    }
}
