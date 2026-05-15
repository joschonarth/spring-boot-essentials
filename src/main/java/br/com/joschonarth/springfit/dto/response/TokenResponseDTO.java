package br.com.joschonarth.springfit.dto.response;

public record TokenResponseDTO(String token, long expiresIn) {
}
