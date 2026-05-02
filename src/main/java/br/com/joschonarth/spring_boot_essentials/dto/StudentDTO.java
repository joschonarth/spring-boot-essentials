package br.com.joschonarth.spring_boot_essentials.dto;

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
public class StudentDTO {

    @NotNull
    private String name;

    @NotNull
    private String email;

    private LocalDate birthDate;
}
