package delivery.shop.shop;

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

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "introduction")
    private String introduction;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "shop")
    private List<Menu> menuList = new ArrayList<>();

    @Embedded
    private ShopLocation location;


    @Builder
    public Shop(String shopName, String phoneNumber, String introduction, ShopLocation location, List<Menu> menuList) {
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
