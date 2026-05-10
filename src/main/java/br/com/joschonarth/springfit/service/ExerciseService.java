package br.com.joschonarth.springfit.service;

import br.com.joschonarth.springfit.database.model.ExerciseEntity;
import br.com.joschonarth.springfit.database.repository.IExerciseRepository;
import br.com.joschonarth.springfit.dto.ExerciseDTO;
import br.com.joschonarth.springfit.dto.ExerciseResponseDTO;
import br.com.joschonarth.springfit.enums.DifficultyLevel;
import br.com.joschonarth.springfit.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final IExerciseRepository exerciseRepository;

    public List<ExerciseResponseDTO> findAll() {
        return exerciseRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public void save(ExerciseDTO exerciseDTO) {
        ExerciseEntity exercise = ExerciseEntity.builder()
                .name(exerciseDTO.getName())
                .muscleGroup(exerciseDTO.getMuscleGroup())
                .equipment(exerciseDTO.getEquipment())
                .description(exerciseDTO.getDescription())
                .difficultyLevel(
                        exerciseDTO.getDifficultyLevel() != null
                                ? exerciseDTO.getDifficultyLevel()
                                : DifficultyLevel.BEGINNER
                )
                .build();

        exerciseRepository.save(exercise);
    }

    public ExerciseResponseDTO getExerciseById(UUID id) throws NotFoundException {
        ExerciseEntity exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Exercise not found"));
        return toResponseDTO(exercise);
    }

    public List<ExerciseResponseDTO> getExerciseByMuscleGroup(String muscleGroup) {
        return exerciseRepository.findByMuscleGroupIgnoreCase(muscleGroup).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private ExerciseResponseDTO toResponseDTO(ExerciseEntity exercise) {
        return ExerciseResponseDTO.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .muscleGroup(exercise.getMuscleGroup())
                .equipment(exercise.getEquipment())
                .description(exercise.getDescription())
                .difficultyLevel(exercise.getDifficultyLevel())
                .createdAt(exercise.getCreatedAt())
                .build();
    }
}
