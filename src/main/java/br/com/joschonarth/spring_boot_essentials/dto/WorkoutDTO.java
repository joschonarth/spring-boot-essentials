package br.com.joschonarth.spring_boot_essentials.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class WorkoutDTO {

    @NotNull
    private UUID studentId;

    @NotBlank
    private String name;

    private String objective;

    @NotEmpty
    private List<UUID> exerciseId;
}
