package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "bacia_item")
public class BaciaItem {

    @Id
    private Long id;

    @Setter
    @Column(name = "bacia_id", unique = true, nullable = false)
    @NotBlank
    private Long baciaId;

    @Setter
    @Column(name = "item_id", nullable = false)
    @NotBlank
    private Long itemId;

    @Setter
    @Column(name = "valor_previsto", precision = 20, scale = 2, nullable = false)
    @NotBlank
    private BigDecimal valorPrevisto;

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

