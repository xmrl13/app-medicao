package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ItemDTO {

    private String nome;
    private BigDecimal previsto;
    private Long obraBaciaId;


    public ItemDTO(String nome, BigDecimal previsto, Long obraBaciaId) {
        this.nome = nome;
        this.previsto = previsto;
        this.obraBaciaId = obraBaciaId;
    }
}
