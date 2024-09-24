package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "itens", uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "unidade_medida"})})
public class Item {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "nome", nullable = false)
    @NotBlank
    private String nome;

    @Getter
    @Setter
    @Column(name = "unidade_medida", nullable = false)
    @NotBlank
    private String unidadeMedida;

    public Item() {
    }

    public Item(String nome, String unidadeMedida) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
    }
}
