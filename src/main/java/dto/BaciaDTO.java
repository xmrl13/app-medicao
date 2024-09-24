package dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaciaDTO {

    @NotBlank(message = "O nome nao pode ser vazio")
    private String nome;

    @NotBlank(message = "O contrato nao pode ser vazio")
    private String contratoObra;

    public BaciaDTO(String nome, String contratoObra) {
        this.nome = nome;
        this.contratoObra = contratoObra;
    }
}
