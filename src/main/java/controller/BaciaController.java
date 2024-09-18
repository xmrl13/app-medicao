package controller;

import dto.BaciaDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import service.BaciaService;

@RestController
@RequestMapping("/api/bacias")
public class BaciaController {

    private final RestTemplate restTemplate;

    private final BaciaService baciaService;

    public BaciaController(RestTemplate restTemplate, BaciaService baciaService) {
        this.restTemplate = restTemplate;
        this.baciaService = baciaService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> criarBacia(@RequestBody BaciaDTO baciaDTO, HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Boolean> response = restTemplate
                    .exchange("http://localhost:8080/api/obras/exists/"
                            + baciaDTO.getContratoObra(), HttpMethod.GET, entity, Boolean.class);

            if (Boolean.TRUE.equals(response.getBody())) {
                try {
                    baciaService.criarBacia(baciaDTO);
                    return ResponseEntity.ok("Bacia criada com sucesso");
                } catch (IllegalArgumentException e) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
                } catch(DataIntegrityViolationException e) {
                    throw new IllegalArgumentException("Bacia com o nome: " + baciaDTO.getNome() + " já existe para o contrato: " + baciaDTO.getContratoObra());
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não encontrada com base no contrato: " + baciaDTO.getContratoObra());
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao comunicar com o serviço de obras");
        }
    }

}