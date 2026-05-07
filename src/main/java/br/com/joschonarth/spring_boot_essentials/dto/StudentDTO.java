package br.com.joschonarth.spring_boot_essentials.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request body for creating a student")
public class StudentDTO {

    @Schema(description = "Student full name", example = "John Doe")
    @NotNull
    private String name;

    @Schema(description = "Student email", example = "john@email.com")
    @NotNull
    private String email;

    @Schema(description = "Student birth date", example = "2000-01-15")
    private LocalDate birthDate;
}
