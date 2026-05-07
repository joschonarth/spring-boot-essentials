package br.com.joschonarth.spring_boot_essentials.service;

import br.com.joschonarth.spring_boot_essentials.config.TokenProvider;
import br.com.joschonarth.spring_boot_essentials.database.model.RolesEntity;
import br.com.joschonarth.spring_boot_essentials.database.model.StudentEntity;
import br.com.joschonarth.spring_boot_essentials.database.repository.IRolesRepository;
import br.com.joschonarth.spring_boot_essentials.database.repository.IStudentRepository;
import br.com.joschonarth.spring_boot_essentials.dto.LoginRequestDTO;
import br.com.joschonarth.spring_boot_essentials.dto.RegisterRequestDTO;
import br.com.joschonarth.spring_boot_essentials.dto.TokenResponseDTO;
import br.com.joschonarth.spring_boot_essentials.enums.RoleTypeEnum;
import br.com.joschonarth.spring_boot_essentials.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IStudentRepository studentRepository;
    private final IRolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public void register(RegisterRequestDTO dto) throws BadRequestException {
        StudentEntity student = studentRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if (student != null) {
            throw new BadRequestException("Student already registered with this email");
        }

        RolesEntity role = rolesRepository.findByName(RoleTypeEnum.ROLE_STUDENT.name())
                .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                        .name(RoleTypeEnum.ROLE_STUDENT.name())
                        .build()
                ));

        studentRepository.save(StudentEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .roles(Set.of(role))
                .password(passwordEncoder.encode(dto.getPassword()))
                .build());
    }

    public TokenResponseDTO login(LoginRequestDTO dto) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
            String token = tokenProvider.generateToken(authentication);

            return new TokenResponseDTO(token, expirationTime);
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Invalid credentials");
        }
    }
}
