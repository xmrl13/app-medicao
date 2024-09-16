package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class MedicaoItemDTO {

    @Getter
    @Setter
    private Long itemId;

    @Getter
    @Setter
    private BigDecimal valorMedido;


}
