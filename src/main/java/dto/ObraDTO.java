package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ObraDTO {

    private String nome;
    private String contrato;
    private BigDecimal orcamento;
    private Long pessoaId;

    public ObraDTO() {
    }

    //Construtor para criar obra
    public ObraDTO(String nome, String contrato, BigDecimal orcamento) {
        this.nome = nome;
        this.contrato = contrato;
        this.orcamento = orcamento;
    }

    //Construtor para cadastrar pessoa em obra
    public ObraDTO(Long pessoaId, String contrato) {
        this.pessoaId = pessoaId;
        this.contrato = contrato;
    }
}
