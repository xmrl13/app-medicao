package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bacias", uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "contrato_obra"})})
public class Bacia {

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
    @Column(name = "contrato_obra", nullable = false)
    @NotBlank
    private String contratoObra;

    public Bacia() {
    }

    public Bacia(String nome, String contratoObra) {
        this.nome = nome;
        this.contratoObra = contratoObra;
    }
}
