package controller;

import dto.BaciaDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BaciaService baciaService;

    @PostMapping("/create")
    public ResponseEntity<String> criarBacia(@RequestBody BaciaDTO baciaDTO, HttpServletRequest request) {

        // O token já foi validado pelo filtro JWT, então só precisamos pegá-lo para a requisição
        String token = request.getHeader("Authorization");

        // Preparando o cabeçalho da nova requisição com o token JWT
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        try {
            // Faz uma requisição GET para o controlador de obras para verificar se a obra existe
            ResponseEntity<Boolean> response = restTemplate.exchange(
                    "http://localhost:8080/api/obras/exists/" + baciaDTO.getContratoObra(),
                    HttpMethod.GET,
                    entity,
                    Boolean.class
            );

            // Verifica se a obra foi encontrada
            if (Boolean.TRUE.equals(response.getBody())) {
                // Se a obra existe, cria a nova bacia
                baciaService.criarBacia(baciaDTO);
                return ResponseEntity.ok("Bacia criada com sucesso");
            } else {
                // Se a obra não foi encontrada, retorna uma mensagem de erro
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não encontrada");
            }

        } catch (Exception e) {
            // Trate exceções relacionadas à comunicação entre os serviços
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao comunicar com o serviço de obras");
        }
    }
}
