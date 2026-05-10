package br.com.joschonarth.springfit.service;

import br.com.joschonarth.springfit.database.model.PhysicalAssessmentEntity;
import br.com.joschonarth.springfit.database.model.StudentEntity;
import br.com.joschonarth.springfit.database.repository.IPhysicalAssessmentRepository;
import br.com.joschonarth.springfit.database.repository.IStudentRepository;
import br.com.joschonarth.springfit.dto.PhysicalAssessmentDTO;
import br.com.joschonarth.springfit.dto.PhysicalAssessmentProjection;
import br.com.joschonarth.springfit.exception.BadRequestException;
import br.com.joschonarth.springfit.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhysicalAssessmentService {

    private final IStudentRepository studentRepository;
    private final IPhysicalAssessmentRepository physicalAssessmentRepository;

    public void createPhysicalAssessment(PhysicalAssessmentDTO physicalAssessmentDTO) throws NotFoundException, BadRequestException {
        StudentEntity student = studentRepository.findById(physicalAssessmentDTO.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student not found"));

        PhysicalAssessmentEntity physicalAssessment = student.getPhysicalAssessment();
        if (physicalAssessment != null) {
            throw new BadRequestException("Physical assessment already registered for this student");
        }

        physicalAssessment = PhysicalAssessmentEntity.builder()
                .weight(physicalAssessmentDTO.getWeight())
                .height(physicalAssessmentDTO.getHeight())
                .bodyFatPercentage(physicalAssessmentDTO.getBodyFatPercentage())
                .build();

        student.setPhysicalAssessment(physicalAssessment);
        studentRepository.save(student);
    }

    public List<PhysicalAssessmentProjection> getAllAssessments() {
        return physicalAssessmentRepository.getAllAssessments();
    }

    public Page<PhysicalAssessmentProjection> getAllAssessmentsPageable(Integer page, Integer size) {
        return physicalAssessmentRepository.getAllAssessmentsPage(PageRequest.of(page, size));
    }
}
