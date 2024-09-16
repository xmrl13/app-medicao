package dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaciaDTO {

    private String nome;

    private String contratoObra;

    public BaciaDTO(String nome, String contratoObra) {
        this.nome = nome;
        this.contratoObra = contratoObra;
    }
}
