package dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDTO {

    private String nome;
    private String unidadeMedida;

    public ItemDTO(String nome, String unidadeMedida) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
    }


}
