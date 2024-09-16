package service;

import dto.PessoaRequestDTO;
import dto.PessoaResponseDTO;
import model.Pessoa;
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

    public PessoaResponseDTO criarOuAtualizarPessoa(PessoaRequestDTO pessoaRequestDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaRequestDTO.getNome());
        pessoa.setEmail(pessoaRequestDTO.getEmail());

        // Verifique se a senha não está nula
        if (pessoaRequestDTO.getPassword() != null) {
            // Criptografar a senha antes de salvar
            String senhaCriptografada = passwordEncoder.encode(pessoaRequestDTO.getPassword());
            pessoa.setPassword(senhaCriptografada);
        } else {
            throw new IllegalArgumentException("Senha não pode ser nula");
        }

        pessoa.setRole(pessoaRequestDTO.getRole());

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        return new PessoaResponseDTO(pessoaSalva.getNome(), pessoaSalva.getEmail(), pessoaSalva.getRole());
    }
}
