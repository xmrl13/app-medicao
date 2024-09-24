package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Setter
@Getter
public class MedicaoDTO {

    @Getter
    @Setter
    @NotBlank
    private String contratoObra;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;

    @NotNull
    private YearMonth competencia;

    private List<MedicaoItemDTO> itensMedidos;

    public MedicaoDTO(String contratoObra, LocalDate dataInicio, LocalDate dataFim, YearMonth competencia) {
        this.contratoObra = contratoObra;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.competencia = competencia;
    }
}
