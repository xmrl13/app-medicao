package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "medicoes_itens")
public class MedicaoItem {

    @Getter
    @Setter
    @EmbeddedId
    private MedicaoItemId id;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("medicaoId")
    @JoinColumn(name = "medicao_id")
    private Medicao medicao;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @Getter
    @Setter
    @Column(precision = 20, scale = 2)
    private BigDecimal valorMedido;

    public MedicaoItem() {
    }

    public MedicaoItem(Medicao medicao, Item item, BigDecimal valorMedido) {
        this.id = new MedicaoItemId(medicao.getId(), item.getId());
        this.medicao = medicao;
        this.item = item;
        this.valorMedido = valorMedido;
    }
}
