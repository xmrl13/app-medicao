package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "itens")
public class Item {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column
    private String nome;

    public Item() {
    }

    public Item(String nome, BigDecimal previsto, BigDecimal jaMedido, Set<BaciaItem> baciaItens, List<MedicaoBaciaItem> medicoesItens) {
        this.nome = nome;
    }
}
