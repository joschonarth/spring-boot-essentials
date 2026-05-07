package br.com.joschonarth.spring_boot_essentials.controller;

import br.com.joschonarth.spring_boot_essentials.database.model.ExerciseEntity;
import br.com.joschonarth.spring_boot_essentials.dto.ExerciseDTO;
import br.com.joschonarth.spring_boot_essentials.dto.ExerciseResponseDTO;
import br.com.joschonarth.spring_boot_essentials.exception.NotFoundException;
import br.com.joschonarth.spring_boot_essentials.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Exercise", description = "Endpoints for exercise management")
@RestController
@RequestMapping("v1/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Operation(summary = "List all exercises")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exercises retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseEntity> findAll() {
        return exerciseService.findAll();
    }

    @Operation(summary = "Create a new exercise")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Exercise created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden - only ADMIN can create exercises")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExercise(@Valid @RequestBody ExerciseDTO exerciseDTO) {
        exerciseService.save(exerciseDTO);
    }

    @Operation(summary = "List exercises by muscle group")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exercises retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/group/{muscleGroup}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseEntity> getExerciseByMuscleGroup(
            @Parameter(description = "Muscle group name", example = "CHEST")
            @PathVariable String muscleGroup) {
        return exerciseService.getExerciseByMuscleGroup(muscleGroup);
    }

    @Operation(summary = "Get exercise by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exercise retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Exercise not found")
    })
    @GetMapping("{exerciseId}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseResponseDTO getExerciseById(
            @Parameter(description = "Exercise ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID exerciseId) throws NotFoundException {
        return exerciseService.getExerciseById(exerciseId);
    }

}
