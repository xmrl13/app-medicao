package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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

    @Getter
    @Setter
    @Column(precision = 20, scale = 2)
    private BigDecimal previsto;

    @Getter
    @Setter
    @Column(precision = 20, scale =2)
    private BigDecimal jaMedido;


    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<BaciaItem> baciaItens;

    @Getter
    @Setter
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<MedicaoItem> medicoesItens;

    public Item() {
    }

    public Item(String nome, BigDecimal previsto, BigDecimal jaMedido, Set<BaciaItem> baciaItens, List<MedicaoItem> medicoesItens) {
        this.nome = nome;
        this.previsto = previsto;
        this.jaMedido = jaMedido;
        this.baciaItens = baciaItens;
        this.medicoesItens = medicoesItens;
    }
}
