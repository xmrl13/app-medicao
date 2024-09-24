package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@Entity
@Table(name = "medicoes")
public class Medicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter
    @Column(name = "contrato_obra", nullable = false)
    @NotBlank
    private String contratoObra;


    @Setter
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Setter
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @NotBlank
    private YearMonth competencia;

    public Medicao() {
    }


    public Medicao(String contratoObra, LocalDate dataInicio, LocalDate dataFim, YearMonth competencia) {
        this.contratoObra = contratoObra;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.competencia = competencia;
    }
}
