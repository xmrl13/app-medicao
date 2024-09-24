package controller;

import dto.ObraDTO;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import service.ObraService;

import java.util.*;

@RestController
@RequestMapping("api/obras")
public class ObraController {

    private final ObraService obraService;

    public ObraController(ObraService obraService) {
        this.obraService = obraService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> criarObra(@Valid @RequestBody ObraDTO obraDTO) {
        obraService.criarObra(obraDTO);
        return ResponseEntity.ok(obraDTO);

    }

    @GetMapping("/get")
    public ResponseEntity<List<ObraDTO>> getAllObras() {
        List<ObraDTO> obras = obraService.getAllObras();
        return ResponseEntity.ok(obras);
    }

    @GetMapping("/search/{contrato}")
    public ResponseEntity<?> findByContract(@PathVariable("contrato") String contrato) {
        ObraDTO obraDTO = obraService.findByContrato(contrato);
        return ResponseEntity.ok(obraDTO);
    }


    @GetMapping("/exists/{contrato}")
    public ResponseEntity<Boolean> exists(@PathVariable String contrato) {
        boolean existe = obraService.obraExistePorContrato(contrato);
        return ResponseEntity.ok(existe);
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
