package controller;

import dto.BaciaDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.BaciaService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/bacias")
public class BaciaController {

    private final RestTemplate restTemplate;

    private final BaciaService baciaService;

    public BaciaController(RestTemplate restTemplate, BaciaService baciaService) {
        this.restTemplate = restTemplate;
        this.baciaService = baciaService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> criarBacia(@Valid @RequestBody BaciaDTO baciaDTO, HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:8080/api/obras/exists/" + baciaDTO.getContratoObra(), HttpMethod.GET, entity, Boolean.class);

            if (Boolean.TRUE.equals(response.getBody())) {
                BaciaDTO baciaSalva = baciaService.criarBacia(baciaDTO);
                return ResponseEntity.ok(baciaSalva);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não encontrada para o contrato: " + baciaDTO.getContratoObra());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro de integridade de dados: " + Objects.requireNonNull(ex.getRootCause()).getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: " + ex.getMessage());
    }
}