package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "bacias", uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "contrato_obra"})})
public class Bacia {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "contrato_obra", nullable = false)
    private String  contratoObra;

    @Getter
    @Setter
    @Column(name = "nome",nullable = false)
    private String nome;

    public Bacia() {}

}
