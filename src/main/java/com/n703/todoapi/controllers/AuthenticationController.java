package com.n703.todoapi.controllers;

import com.n703.todoapi.dtos.request.SignInRequestDTO;
import com.n703.todoapi.dtos.request.SignUpRequestDTO;
import com.n703.todoapi.dtos.response.UserResponseDTO;
import com.n703.todoapi.infra.TokenService;
import com.n703.todoapi.models.User;
import com.n703.todoapi.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationManager manager, TokenService tokenService, AuthenticationService authenticationService) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<UserResponseDTO> signIn(@RequestBody SignInRequestDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authentication = manager.authenticate(authenticationToken);
        
        var user = (User) authentication.getPrincipal();
        var tokenJWT = tokenService.generateToken(user);

        return ResponseEntity.ok(new UserResponseDTO(user.getName(), user.getEmail(), tokenJWT));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signIn(@RequestBody SignUpRequestDTO dto) {
        authenticationService.create(dto);
        return ResponseEntity.ok().build();
    }

}
