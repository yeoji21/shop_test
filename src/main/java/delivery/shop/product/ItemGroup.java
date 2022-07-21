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
public class ItemGroup {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private int minimumSelectCount;
    private int maximumSelectCount;

    @Builder
    public ItemGroup(String name, int minimumSelectCount, int maximumSelectCount) {
        this.name = name;
        this.minimumSelectCount = minimumSelectCount;
        this.maximumSelectCount = maximumSelectCount;
    }
}
