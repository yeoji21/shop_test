package delivery.shop.shop;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SecondaryTable(
        name = "shop_location",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")
)
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

    @AttributeOverrides({
            @AttributeOverride(
                    name = "streetAddress",
                    column = @Column(table = "shop_location", name = "stree_address")
            ),
            @AttributeOverride(
                    name = "latitude",
                    column = @Column(table = "shop_location", name = "latitude")
            ),
            @AttributeOverride(
                    name = "longitude",
                    column = @Column(table = "shop_location", name = "longitude")
            )
    })
    @Embedded
    private Location location;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "shop")
    private List<Menu> menuList = new ArrayList<>();


    @Builder
    public Shop(String shopName, String phoneNumber, String introduction, Location location, List<Menu> menuList) {
        this.shopName = shopName;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.location = location;
//        menuList.forEach(this::addMenu);
    }

    public void addMenu(Menu menu) {
        menuList.add(menu);
        menu.setShop(this);
    }
}
