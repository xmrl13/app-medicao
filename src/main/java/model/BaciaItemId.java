package model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BaciaItemId implements Serializable {

    private Long baciaId;
    private Long itemId;

    // Construtores, getters e setters

    public BaciaItemId() {}

    public BaciaItemId(Long baciaId, Long itemId) {
        this.baciaId = baciaId;
        this.itemId = itemId;
    }

    public Long getBaciaId() {
        return baciaId;
    }

    public void setBaciaId(Long baciaId) {
        this.baciaId = baciaId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaciaItemId)) return false;
        BaciaItemId that = (BaciaItemId) o;
        return Objects.equals(getBaciaId(), that.getBaciaId()) &&
                Objects.equals(getItemId(), that.getItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBaciaId(), getItemId());
    }
}
