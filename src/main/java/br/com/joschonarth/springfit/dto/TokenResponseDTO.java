package br.com.joschonarth.springfit.dto;

public record TokenResponseDTO(String token, long expiresIn) {
}
