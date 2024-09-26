package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "medicoes_bacias_itens")
public class MedicaoBaciaItem {

    @Id
    private Long id;

    @Setter
    @Column(nullable = false)
    @NotBlank
    private Long medicaoId;

    @Setter
    @Column(nullable = false)
    @NotBlank
    private Long baciaItenId;

    @Setter
    @Column(nullable = false, precision = 20, scale = 2)
    @NotBlank
    private BigDecimal valor_medido;
}
