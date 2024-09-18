package security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.auth.AuthRequest;
import security.auth.AuthResponse;
import security.service.CustomUserDetailsService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest) {
        AuthResponse novoLogado = customUserDetailsService.login(authRequest);
        return ResponseEntity.ok(novoLogado);
    }
}
