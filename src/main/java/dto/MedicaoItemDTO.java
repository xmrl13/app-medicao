package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MedicaoItemDTO {

    private Long itemId;

    private BigDecimal valorMedido;


}

