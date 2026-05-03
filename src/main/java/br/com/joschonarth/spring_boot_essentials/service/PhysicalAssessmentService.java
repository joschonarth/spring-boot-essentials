package br.com.joschonarth.spring_boot_essentials.service;

import br.com.joschonarth.spring_boot_essentials.database.model.PhysicalAssessmentEntity;
import br.com.joschonarth.spring_boot_essentials.database.model.StudentEntity;
import br.com.joschonarth.spring_boot_essentials.database.repository.IPhysicalAssessmentRepository;
import br.com.joschonarth.spring_boot_essentials.database.repository.IStudentRepository;
import br.com.joschonarth.spring_boot_essentials.dto.PhysicalAssessmentDTO;
import br.com.joschonarth.spring_boot_essentials.dto.PhysicalAssessmentProjection;
import br.com.joschonarth.spring_boot_essentials.exception.BadRequestException;
import br.com.joschonarth.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
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
}
