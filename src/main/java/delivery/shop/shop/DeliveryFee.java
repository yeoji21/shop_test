package delivery.shop.shop;

import delivery.common.domain.Money;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Getter
@EqualsAndHashCode(exclude = "id", callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "delivery_fee")
@Entity
public class DeliveryFee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "order_price"))
    private Money orderPrice;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "fee"))
    private Money fee;

    public DeliveryFee(Money orderPrice, Money fee) {
        this.orderPrice = orderPrice;
        this.fee = fee;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
