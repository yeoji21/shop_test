package delivery.shop.shop;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Shop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "introduction")
    private String introduction;

    @OneToOne(
//            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "shop",
            optional = false
    )
    private Location location;

    @Builder
    public Shop(String shopName, String phoneNumber, String introduction, Location location) {
        this.shopName = shopName;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.location = location;
        location.setShop(this);
    }

    public void setLocation(Location location) {
        this.location = location;
        location.setShop(this);
    }
}
