package delivery.shop.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "stock_id", unique = true)
    private Stock stock;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            mappedBy = "product"
    )
    private List<ProductItemGroup> productItemGroups = new ArrayList<>();

    private String name;

    private int price;

    @Builder
    public Product(Stock stock, String name, int price) {
        this.stock = stock;
        this.name = name;
        this.price = price;
    }
}
