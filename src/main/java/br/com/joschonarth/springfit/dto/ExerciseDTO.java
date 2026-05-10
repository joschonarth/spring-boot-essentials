package br.com.joschonarth.springfit.dto;

import br.com.joschonarth.springfit.enums.DifficultyLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Schema(description = "Request body for creating an exercise")
public class ExerciseDTO {

    @Schema(description = "Exercise name", example = "Bench Press")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Muscle group targeted", example = "CHEST")
    @NotBlank(message = "Muscle group is required")
    private String muscleGroup;

    @Schema(description = "Equipment needed", example = "Barbell")
    private String equipment;

    @Schema(description = "Difficulty level", example = "INTERMEDIATE")
    private DifficultyLevel difficultyLevel;
}
