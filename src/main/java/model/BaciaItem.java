package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "bacia_item")
public class BaciaItem {

    @Getter
    @Id
    private Long id;


    @Getter
    @Setter
    @Column(name = "bacia_id", unique = true, nullable = false)
    private Long baciaId;

    @Getter
    @Setter
    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Getter
    @Setter
    @Column(name = "valor_previsto", precision = 20, scale = 2, nullable = false)
    private BigDecimal valorPrevisto;

    @Getter
    @Setter
    @Column(name = "valor_acumulado", precision = 20, scale = 2, nullable = true)
    private BigDecimal valorAcumulado;

    public BaciaItem() {
    }

    public BaciaItem(Long baciaId, Long itemId, BigDecimal valorPrevisto, BigDecimal valorAcumulado) {
        this.baciaId = baciaId;
        this.itemId = itemId;
        this.valorPrevisto = valorPrevisto;
        this.valorAcumulado = valorAcumulado;
    }
}

