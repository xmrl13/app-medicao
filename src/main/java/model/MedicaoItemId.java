package model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MedicaoItemId implements Serializable {

    private Long medicaoId;
    private Long itemId;

    public MedicaoItemId() {
    }

    public MedicaoItemId(Long medicaoId, Long itemId) {
        this.medicaoId = medicaoId;
        this.itemId = itemId;
    }

    public Long getMedicaoId() {
        return medicaoId;
    }

    public Long getItemId() {
        return itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicaoItemId that = (MedicaoItemId) o;
        return Objects.equals(medicaoId, that.medicaoId) && Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicaoId, itemId);
    }
}
