package service;

import dto.PessoaRequestDTO;
import dto.PessoaResponseDTO;
import jakarta.transaction.Transactional;
import model.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.PessoaRepository;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PasswordEncoder passwordEncoder;


    public PessoaService(PessoaRepository pessoaRepository, PasswordEncoder passwordEncoder) {
        this.pessoaRepository = pessoaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseEntity<?> createPessoa(PessoaRequestDTO pessoaRequestDTO) {

        pessoaRepository.findByEmail(pessoaRequestDTO.getEmail()).ifPresent(pessoa -> {
            throw new IllegalArgumentException("Email já cadastrado.");
        });

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaRequestDTO.getNome());
        pessoa.setEmail(pessoaRequestDTO.getEmail());

        String senhaCriptografada = passwordEncoder.encode(pessoaRequestDTO.getPassword());
        pessoa.setPassword(senhaCriptografada);
        pessoa.setRole(pessoaRequestDTO.getRole());


        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    public ResponseEntity<?> deleteByEmail(String email) {
        pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada com o e-mail: " + email));

        pessoaRepository.deleteByEmail(email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
