package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MedicaoDTO {

    private Long medicaoId;
    private List<MedicaoItemDTO> itensMedidos;

    public Long getMedicaoId() {
        return medicaoId;
    }

    public List<MedicaoItemDTO> getItensMedidos() {
        return itensMedidos;
    }

    public void setItensMedidos(List<MedicaoItemDTO> itensMedidos) {
        this.itensMedidos = itensMedidos;
    }
}
