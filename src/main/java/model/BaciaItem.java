package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bacia_item")
public class BaciaItem {

    @Getter
    @EmbeddedId
    private BaciaItemId id = new BaciaItemId();

    @Getter
    @Setter
    @ManyToOne
    @MapsId("baciaId")  // Mapeia o campo baciaId no BaciaItemId
    @JoinColumn(name = "bacia_id")
    private Bacia bacia;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("itemId")  // Mapeia o campo itemId no BaciaItemId
    @JoinColumn(name = "item_id")
    private Item item;

    @Getter
    @Setter
    @Column(nullable = false)
    private Integer valorPrevisto;


    public BaciaItem() {}

    public BaciaItem(Bacia bacia, Item item, Integer valorPrevisto) {
        this.bacia = bacia;
        this.item = item;
        this.valorPrevisto = valorPrevisto;
        this.id = new BaciaItemId(bacia.getId(), item.getId());
    }

}
