package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "medicoes_bacias_itens")
public class MedicaoBaciaItem {

    @Getter
    @Id
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private Long medicaoId;

    @Getter
    @Setter
    @Column(nullable = false)
    private Long baciaItenId;

    @Getter
    @Setter
    @Column(nullable = false, precision = 20, scale = 2)
    private BigDecimal valor_medido;
}
