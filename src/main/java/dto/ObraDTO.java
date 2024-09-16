package dto;

import java.math.BigDecimal;

public class ObraDTO {

    private String nome;
    private String contrato;
    private BigDecimal orcamento;

    public ObraDTO() {
    }

    public ObraDTO(String nome, String contrato, BigDecimal orcamento) {
        this.nome = nome;
        this.contrato = contrato;
        this.orcamento = orcamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(BigDecimal orcamento) {
        this.orcamento = orcamento;
    }
}
