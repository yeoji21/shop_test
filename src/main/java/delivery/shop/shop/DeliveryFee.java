package delivery.shop.shop;

import delivery.common.domain.Money;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class DeliveryFee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverride(name = "value", column = @Column(name = "order_price"))
    private Money orderPrice;

    @AttributeOverride(name = "value", column = @Column(name = "fee"))
    private Money fee;

    public DeliveryFee(Money orderPrice, Money fee) {
        this.orderPrice = orderPrice;
        this.fee = fee;
    }
}
