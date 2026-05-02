package br.com.joschonarth.spring_boot_essentials.dto;

import br.com.joschonarth.spring_boot_essentials.enums.DifficultyLevel;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ExerciseDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Muscle group is required")
    private String muscleGroup;
    private String equipment;
    private DifficultyLevel difficultyLevel;
}
