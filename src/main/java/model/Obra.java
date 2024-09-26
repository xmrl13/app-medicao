package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "obras")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    @NotBlank
    private String nome;

    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    @NotBlank
    private String contrato;

    @Getter
    @Setter
    @Column(precision = 30, scale = 2, nullable = false)
    private BigDecimal orcamento;

    @Getter
    @Setter
    @Column(name = "pessoas_id")
    private Long idPessoa;

    public Obra() {
    }

    public Obra(String nome, String contrato, BigDecimal orcamento) {
        this.nome = nome;
        this.contrato = contrato;
        this.orcamento = orcamento;
    }
}
