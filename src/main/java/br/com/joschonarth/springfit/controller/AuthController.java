package br.com.joschonarth.springfit.controller;

import br.com.joschonarth.springfit.dto.LoginRequestDTO;
import br.com.joschonarth.springfit.dto.RegisterRequestDTO;
import br.com.joschonarth.springfit.dto.TokenResponseDTO;
import br.com.joschonarth.springfit.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Endpoints for user authentication")
@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Register a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Email already in use or invalid data")
    })
    @PostMapping("register")
    public void register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) throws Exception {
        authenticationService.register(registerRequestDTO);
    }

    @Operation(summary = "Authenticate user and return JWT token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "400", description = "Invalid credentials")
    })
    @PostMapping("login")
    public TokenResponseDTO login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws Exception {
        return authenticationService.login(loginRequestDTO);
    }
}
