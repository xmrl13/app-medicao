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
    @Column(name = "obra_id", nullable = false)
    private String obraId;


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


    public Medicao(String obraId, LocalDate dataInicio, LocalDate dataFim) {
        this.obraId = obraId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}
