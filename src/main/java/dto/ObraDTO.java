package dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ObraDTO {

    @NotBlank(message = "O nome da obra é obrigatório.")
    @Size(min = 5, message = "O nome da obra deve ter pelo menos 5 caracteres e comecar com SES")
    private String nome;

    @NotBlank(message = "O contrato é obrigatório.")
    private String contrato;

    @NotNull(message = "O orçamento é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O orçamento deve ser maior que zero.")
    private BigDecimal orcamento;

    private Long pessoaId;

    public ObraDTO() {
    }


    public ObraDTO(String nome, String contrato, BigDecimal orcamento) {
        this.nome = nome;
        this.contrato = contrato;
        this.orcamento = orcamento;
    }
}
