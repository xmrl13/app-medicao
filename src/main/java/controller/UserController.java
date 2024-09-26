package controller;

import dto.EmailDTO;
import dto.UserRequestDTO;
import dto.UserUpdateDTO;
import exceptions.*;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {

        userService.createUser(userRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deletebyemail")
    public ResponseEntity<?> deleteByEmail(@Valid @RequestBody EmailDTO emailDTO) {

        userService.deleteByEmail(emailDTO.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {

        userService.updateUser(userUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/read")
    public ResponseEntity<?> find(@RequestParam("email") String email) throws ResourceNotFoundException {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(email);
        return ResponseEntity.ok().body(userService.readByEmail(emailDTO));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro de integridade de dados: " + Objects.requireNonNull(e.getRootCause()).getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: " + e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro de email: " + e.getMessage());
    }


    @ExceptionHandler(InvalidSecretPhraseException.class)
    public ResponseEntity<?> handleInvalidSecretPhraseException(InvalidSecretPhraseException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro inesperado" + e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro inesperado" + e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro inesperado" + e.getMessage());
    }

}




