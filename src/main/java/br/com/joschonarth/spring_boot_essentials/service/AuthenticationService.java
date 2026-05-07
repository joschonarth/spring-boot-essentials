package br.com.joschonarth.spring_boot_essentials.service;

import br.com.joschonarth.spring_boot_essentials.database.model.RolesEntity;
import br.com.joschonarth.spring_boot_essentials.database.model.StudentEntity;
import br.com.joschonarth.spring_boot_essentials.database.repository.IRolesRepository;
import br.com.joschonarth.spring_boot_essentials.database.repository.IStudentRepository;
import br.com.joschonarth.spring_boot_essentials.dto.RegisterRequestDTO;
import br.com.joschonarth.spring_boot_essentials.enums.RoleTypeEnum;
import br.com.joschonarth.spring_boot_essentials.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IStudentRepository studentRepository;
    private final IRolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequestDTO dto) throws BadRequestException {
        StudentEntity student = studentRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if (student != null) {
            throw new BadRequestException("Student already registered with this email");
        }

        RolesEntity role = rolesRepository.findByName(RoleTypeEnum.STUDENT.name())
                .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                        .name(RoleTypeEnum.STUDENT.name())
                        .build()
                ));

        studentRepository.save(StudentEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .roles(Set.of(role))
                .password(passwordEncoder.encode(dto.getPassword()))
                .build());
    }
}
