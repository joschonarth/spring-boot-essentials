package br.com.joschonarth.spring_boot_essentials.controller;

import br.com.joschonarth.spring_boot_essentials.database.model.ExerciseEntity;
import br.com.joschonarth.spring_boot_essentials.dto.ExerciseDTO;
import br.com.joschonarth.spring_boot_essentials.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseEntity> findAll() {
        return exerciseService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExercise(@Valid @RequestBody ExerciseDTO exerciseDTO) {
        exerciseService.save(exerciseDTO);
    }

    @GetMapping("/group/{muscleGroup}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseEntity> getExerciseByMuscleGroup(@PathVariable String muscleGroup) {
        return exerciseService.getExerciseByMuscleGroup(muscleGroup);
    }

}
