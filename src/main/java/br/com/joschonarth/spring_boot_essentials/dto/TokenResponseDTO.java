package br.com.joschonarth.spring_boot_essentials.dto;

public record TokenResponseDTO(String token, long expiresIn) {
}
