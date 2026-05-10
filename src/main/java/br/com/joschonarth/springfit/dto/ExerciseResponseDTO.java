package br.com.joschonarth.springfit.dto;

import br.com.joschonarth.springfit.enums.DifficultyLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Exercise response data")
public class ExerciseResponseDTO {

    @Schema(description = "Exercise ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Exercise name", example = "Bench Press")
    private String name;

    @Schema(description = "Muscle group targeted", example = "CHEST")
    private String muscleGroup;

    @Schema(description = "Equipment needed", example = "Barbell")
    private String equipment;

    @Schema(description = "Difficulty level", example = "INTERMEDIATE")
    private DifficultyLevel difficultyLevel;
}