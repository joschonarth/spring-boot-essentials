package br.com.joschonarth.springfit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Student response data")
public class StudentResponseDTO {

    @Schema(description = "Student ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Student full name", example = "John Doe")
    private String name;

    @Schema(description = "Student email", example = "john@email.com")
    private String email;

    @Schema(description = "Student birth date", example = "2000-01-15")
    private LocalDate birthDate;
}