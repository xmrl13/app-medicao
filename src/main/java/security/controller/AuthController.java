package security.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import security.auth.AuthRequest;
import security.auth.AuthResponse;
import security.service.CustomUserDetailsService;

import java.util.Objects;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        AuthResponse novoLogado = customUserDetailsService.login(authRequest);
        return ResponseEntity.ok(novoLogado);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos.");
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

}
