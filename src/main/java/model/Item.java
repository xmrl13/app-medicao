package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "itens", uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "unidade_medida"})})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "nome", nullable = false)
    @NotBlank
    private String nome;

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
