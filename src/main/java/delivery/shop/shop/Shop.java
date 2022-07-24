package delivery.shop.shop;

import delivery.common.domain.Money;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SecondaryTable(
        name = "shop_location",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "shop_id", referencedColumnName = "id")
)
@Entity
public class Shop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shop_name")
    private String shopName;

    @Embedded
    private Money minOrderPrice;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "shop")
    private List<DeliveryFee> deliveryFees = new ArrayList<>();

    @Embedded
    private ShopLocation location;

    @Builder
    public Shop(String shopName, Money minOrderPrice, ShopLocation location) {
        this.shopName = shopName;
        this.minOrderPrice = minOrderPrice;
        this.location = location;
    }

    public void addDeliveryFee(DeliveryFee deliveryFee) {
        deliveryFees.add(deliveryFee);
        deliveryFee.setShop(this);
    }
}





















