package br.com.joschonarth.spring_boot_essentials.controller;

import br.com.joschonarth.spring_boot_essentials.database.model.PhysicalAssessmentEntity;
import br.com.joschonarth.spring_boot_essentials.dto.StudentResponseDTO;
import br.com.joschonarth.spring_boot_essentials.exception.NotFoundException;
import br.com.joschonarth.spring_boot_essentials.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Student", description = "Endpoints for student management")
@RestController
@RequestMapping("v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Get student physical assessment", description = "Students can only view their own assessment. ADMIN can view all.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Assessment retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden - cannot access another student's assessment"),
            @ApiResponse(responseCode = "404", description = "Student or assessment not found")
    })
    @PreAuthorize("#studentId == authentication.principal.id or hasRole('ADMIN')")
    @GetMapping("{studentId}/assessment")
    public PhysicalAssessmentEntity getPhysicalAssessment(
            @Parameter(description = "Student ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID studentId) throws NotFoundException {
        return studentService.getStudentAssessment(studentId);
    }

    @Operation(summary = "Remove a student")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Student removed successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeStudent(
            @Parameter(description = "Student ID", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID studentId) throws NotFoundException {
        studentService.deleteStudent(studentId);
    }

    @Operation(summary = "List all students")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Students retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }
}
