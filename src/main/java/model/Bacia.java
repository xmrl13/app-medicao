package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "bacias")
public class Bacia {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String nome;

    @OneToMany(mappedBy = "bacia", cascade = CascadeType.ALL)
    private Set<BaciaItem> baciaItens;

    @Getter
    @Setter
    @Column(name = "contrato_obra", nullable = false)
    private String  contratoObra;

    public Bacia() {}

    public Bacia(String nome, String contratoObra) {
        this.nome = nome;
        this.contratoObra = contratoObra;
    }

}
