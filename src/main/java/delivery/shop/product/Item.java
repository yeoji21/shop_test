package delivery.shop.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "stock_id", unique = true)
    private Stock stock;

    private String name;

    private int price;

    @Builder
    public Item(Stock stock, String name, int price) {
        this.stock = stock;
        this.name = name;
        this.price = price;
    }
}
