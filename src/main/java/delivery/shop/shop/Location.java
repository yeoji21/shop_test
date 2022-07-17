package delivery.shop.shop;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Location implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "street_address")
    private String streetAddress;

    @Builder
    public Location(Shop shop, String streetAddress) {
        this.shop = shop;
        this.streetAddress = streetAddress;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
