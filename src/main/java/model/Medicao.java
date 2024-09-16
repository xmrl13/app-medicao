package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "medicoes")
public class Medicao {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "obra_id", nullable = false)
    private Obra obra;


    @Getter
    @Setter
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Getter
    @Setter
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    public Medicao() {
    }


    public Medicao(Obra obra, LocalDate dataInicio, LocalDate dataFim) {
        this.obra = obra;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}
