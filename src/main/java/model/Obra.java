package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "obras")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String nome;

    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String contrato;

    @Getter
    @Setter
    @Column(precision = 30, scale = 2, nullable = false)
    private BigDecimal orcamento;

    @Getter
    @Setter
    @OneToMany
    private Set<Pessoa> pessoas = new HashSet<>();

    public Obra() {
    }

    public Obra(String nome, String contrato, BigDecimal orcamento) {
        this.nome = nome;
        this.contrato = contrato;
        this.orcamento = orcamento;
    }
}
