package delivery.shop.shop;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class Location{
    @Column(name = "street_address")
    private String streetAddress;
    private Double latitude;
    private Double longitude;

    public Location(String streetAddress, Double latitude, Double longitude) {
        this.streetAddress = streetAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
