package controller;

import dto.EmailDTO;
import dto.PersonRequestDTO;
import dto.PersonUpdateDTO;
import exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import service.PersonService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {


    private final PersonService personService;

    public PessoaController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPessoa(@Valid @RequestBody PersonRequestDTO personRequestDTO) {
        personService.createPerson(personRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deletebyemail")
    public ResponseEntity<?> deleteByEmail(@Valid @RequestBody EmailDTO emailDTO) {

        personService.deleteByEmail(emailDTO.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> updatePerson(@Valid @RequestBody PersonUpdateDTO personUpdateDTO) {

        personService.updatePerson(personUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/read")
    public ResponseEntity<?> find(@RequestParam("email") String email) throws ResourceNotFoundException {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(email);
        return ResponseEntity.ok().body(personService.readByEmail(emailDTO));
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
    public ResponseEntity<String> resourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro de integridade de dados: " + Objects.requireNonNull(ex.getRootCause()).getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: " + ex.getMessage());
    }
}




