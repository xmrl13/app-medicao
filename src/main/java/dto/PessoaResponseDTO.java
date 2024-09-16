package dto;

import enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class PessoaResponseDTO {

    private String nome;
    private String email;
    private Role role;

    public PessoaResponseDTO() {}

    public PessoaResponseDTO( String nome, String email, Role role) {
        this.nome = nome;
        this.email = email;
        this.role = role;
    }



}
