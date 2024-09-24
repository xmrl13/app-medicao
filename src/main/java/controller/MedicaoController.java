package controller;

import dto.MedicaoDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import service.MedicaoService;

@RestController
@RequestMapping("api/medicoes")
public class MedicaoController {

    private final MedicaoService medicaoService;
    private final RestTemplate restTemplate;


    public MedicaoController(MedicaoService medicaoService, RestTemplate restTemplate) {
        this.medicaoService = medicaoService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMedicao(@Valid @RequestBody MedicaoDTO medicaoDTO, HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);


        try {
            ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:8080/api/obras/exists/" + medicaoDTO.getContratoObra(), HttpMethod.GET, entity, Boolean.class);
            if (Boolean.TRUE.equals(response.getBody())) {
                MedicaoDTO medicaoSalva = medicaoService.createMedicao(medicaoDTO);
                return ResponseEntity.ok(medicaoSalva);
            }
            return ResponseEntity.badRequest().body("Obra não encontrada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
