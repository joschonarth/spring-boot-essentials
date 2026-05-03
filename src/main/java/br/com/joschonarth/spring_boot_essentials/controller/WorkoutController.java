package br.com.joschonarth.spring_boot_essentials.controller;

import br.com.joschonarth.spring_boot_essentials.dto.WorkoutDTO;
import br.com.joschonarth.spring_boot_essentials.exception.BadRequestException;
import br.com.joschonarth.spring_boot_essentials.exception.NotFoundException;
import br.com.joschonarth.spring_boot_essentials.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/workout")
@RequiredArgsConstructor
@Validated
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createWorkout(@Valid @RequestBody WorkoutDTO workoutDTO) throws NotFoundException, BadRequestException {
        workoutService.createWorkout(workoutDTO);
    }
}
