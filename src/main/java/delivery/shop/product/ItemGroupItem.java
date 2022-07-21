package delivery.shop.product;

import delivery.shop.product.id.ItemGroupItemId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(ItemGroupItemId.class)
@Entity
public class ItemGroupItem {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_group_id")
    private ItemGroup itemGroup;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public ItemGroupItem(ItemGroup itemGroup, Item item) {
        this.itemGroup = itemGroup;
        this.item = item;
    }
}
