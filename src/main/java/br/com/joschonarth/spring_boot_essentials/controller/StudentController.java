package br.com.joschonarth.spring_boot_essentials.controller;

import br.com.joschonarth.spring_boot_essentials.database.model.PhysicalAssessmentEntity;
import br.com.joschonarth.spring_boot_essentials.dto.StudentDTO;
import br.com.joschonarth.spring_boot_essentials.exception.BadRequestException;
import br.com.joschonarth.spring_boot_essentials.exception.NotFoundException;
import br.com.joschonarth.spring_boot_essentials.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@Valid @RequestBody StudentDTO studentDTO) throws BadRequestException {
        studentService.createStudent(studentDTO);
    }

    @GetMapping("{studentId}/assessment")
    public PhysicalAssessmentEntity getPhysicalAssessment(@PathVariable UUID studentId) throws NotFoundException {
        return studentService.getStudentAssessment(studentId);
    }
}
