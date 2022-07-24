package delivery.shop.shop;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class ShopLocation {
    @Column(table = "shop_location",
            name = "street_address")
    private String streetAddress;

    @Column(table = "shop_location",
            name = "latitude")
    private Double latitude;

    @Column(table = "shop_location",
            name = "longitude")
    private Double longitude;

    public ShopLocation(String streetAddress, Double latitude, Double longitude) {
        this.streetAddress = streetAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
