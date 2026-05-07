package br.com.joschonarth.spring_boot_essentials.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request body for user registration")
public class RegisterRequestDTO {

    @Schema(description = "User full name", example = "John Doe")
    @NotBlank
    private String name;

    @Schema(description = "User email", example = "john@email.com")
    @NotBlank
    private String email;

    @Schema(description = "User password", example = "secret123")
    @NotBlank
    private String password;

    @Schema(description = "User birth date", example = "2000-01-15")
    private LocalDate birthDate;
}
