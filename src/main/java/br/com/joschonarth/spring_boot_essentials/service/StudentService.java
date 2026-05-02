package br.com.joschonarth.spring_boot_essentials.service;

import br.com.joschonarth.spring_boot_essentials.database.model.StudentEntity;
import br.com.joschonarth.spring_boot_essentials.database.repository.IStudentRepository;
import br.com.joschonarth.spring_boot_essentials.dto.StudentDTO;
import br.com.joschonarth.spring_boot_essentials.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final IStudentRepository studentRepository;

    public void createStudent(StudentDTO studentDTO) throws BadRequestException {
        StudentEntity student = studentRepository.findByEmail(studentDTO.getEmail())
                .orElse(null);

        if (student != null) {
            throw new BadRequestException("Student already registered with this email");
        }

        studentRepository.save(StudentEntity.builder()
                .name(studentDTO.getName())
                .email(studentDTO.getEmail())
                .birthDate(studentDTO.getBirthDate())
                .build());
    }
}
