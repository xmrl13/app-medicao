package controller;

import dto.PessoaRequestDTO;
import dto.PessoaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PessoaService;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {


    @Autowired
    private PessoaService pessoaService;


    @PostMapping("/create")
    public ResponseEntity<PessoaResponseDTO> criarPessoa(@RequestBody PessoaRequestDTO pessoaRequestDTO) {
        PessoaResponseDTO novaPessoa = pessoaService.criarOuAtualizarPessoa(pessoaRequestDTO);
        return ResponseEntity.ok(novaPessoa);
    }
}



