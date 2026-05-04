package br.com.joschonarth.spring_boot_essentials.controller;

import br.com.joschonarth.spring_boot_essentials.dto.PhysicalAssessmentDTO;
import br.com.joschonarth.spring_boot_essentials.dto.PhysicalAssessmentProjection;
import br.com.joschonarth.spring_boot_essentials.exception.BadRequestException;
import br.com.joschonarth.spring_boot_essentials.exception.NotFoundException;
import br.com.joschonarth.spring_boot_essentials.service.PhysicalAssessmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/assessment")
@RequiredArgsConstructor
public class PhysicalAssessmentController {

    private final PhysicalAssessmentService physicalAssessmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPhysicalAssessment(@Valid @RequestBody PhysicalAssessmentDTO physicalAssessmentDTO) throws NotFoundException, BadRequestException {
        physicalAssessmentService.createPhysicalAssessment(physicalAssessmentDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PhysicalAssessmentProjection> getAllAssessments() {
        return physicalAssessmentService.getAllAssessments();
    }

    @GetMapping("page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<PhysicalAssessmentProjection> getAllAssessmentsPageable(@PathVariable Integer page, @PathVariable Integer size) {
        return physicalAssessmentService.getAllAssessmentsPageable(page, size);
    }
}
