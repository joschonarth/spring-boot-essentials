package br.com.joschonarth.spring_boot_essentials.service;

import br.com.joschonarth.spring_boot_essentials.database.model.PhysicalAssessmentEntity;
import br.com.joschonarth.spring_boot_essentials.database.model.StudentEntity;
import br.com.joschonarth.spring_boot_essentials.database.model.WorkoutEntity;
import br.com.joschonarth.spring_boot_essentials.database.repository.IPhysicalAssessmentRepository;
import br.com.joschonarth.spring_boot_essentials.database.repository.IStudentRepository;
import br.com.joschonarth.spring_boot_essentials.database.repository.IWorkoutRepository;
import br.com.joschonarth.spring_boot_essentials.dto.StudentResponseDTO;
import br.com.joschonarth.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final IPhysicalAssessmentRepository physicalAssessmentRepository;
    private final IWorkoutRepository workoutRepository;
    private final IStudentRepository studentRepository;

    public PhysicalAssessmentEntity getStudentAssessment(UUID studentId) throws NotFoundException {
         StudentEntity student = studentRepository.findByIdFetch(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

         PhysicalAssessmentEntity assessment = student.getPhysicalAssessment();

         if (assessment == null) {
             throw new NotFoundException("Physical Assessment not found for this student");
         }

         return assessment;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteStudent(UUID studentId)throws NotFoundException {
        StudentEntity student = studentRepository.findByIdFetch(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        List<UUID> workoutIds = student.getWorkouts().stream()
                .map(WorkoutEntity::getId)
                .toList();

        workoutRepository.deleteAllById(workoutIds);

        studentRepository.deleteById(studentId);

        physicalAssessmentRepository.deleteById(student.getPhysicalAssessment().getId());
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> StudentResponseDTO.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .email(student.getEmail())
                    .birthDate(student.getBirthDate())
                    .build())
                .toList();
    }
}
