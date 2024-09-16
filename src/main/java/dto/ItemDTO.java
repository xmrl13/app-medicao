package dto;

import java.math.BigDecimal;

public class ItemDTO {

    private String nome;
    private BigDecimal previsto;
    private Long obraBaciaId;

    public ItemDTO(String nome, BigDecimal previsto, Long obraBaciaId) {
        this.nome = nome;
        this.previsto = previsto;
        this.obraBaciaId = obraBaciaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrevisto() {
        return previsto;
    }

    public void setPrevisto(BigDecimal previsto) {
        this.previsto = previsto;
    }

    public Long getObraBaciaId() {
        return obraBaciaId;
    }

    public void setObraBaciaId(Long obraBaciaId) {
        this.obraBaciaId = obraBaciaId;
    }
}
