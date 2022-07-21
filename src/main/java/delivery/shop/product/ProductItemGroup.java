package delivery.shop.product;

import delivery.shop.product.id.ProductItemGroupId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(ProductItemGroupId.class)
@Entity
public class ProductItemGroup {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_group_id")
    private ItemGroup itemGroup;

    @Builder
    public ProductItemGroup(Product product, ItemGroup itemGroup) {
        this.product = product;
        this.itemGroup = itemGroup;
    }
}
