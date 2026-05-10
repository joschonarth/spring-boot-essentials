package br.com.joschonarth.spring_boot_essentials.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Workout response data")
public class WorkoutResponseDTO {

    @Schema(description = "Workout ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Workout name", example = "Upper Body A")
    private String name;

    @Schema(description = "Workout objective", example = "Muscle hypertrophy")
    private String objective;

    @Schema(description = "Student ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID studentId;

    @Schema(description = "List of exercises")
    private List<ExerciseResponseDTO> exercises;
}