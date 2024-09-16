package dto;

import enums.Role;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public class PessoaRequestDTO {

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String email;

    @Setter
    @Getter
    private String password;

    @Getter
    @Setter
    private Role role;

    public PessoaRequestDTO() {
    }

    public PessoaRequestDTO(String nome, String email, Role role, String senha) {
        this.nome = nome;
        this.email = email;
        this.password = senha;
        this.role = role;
    }

}
