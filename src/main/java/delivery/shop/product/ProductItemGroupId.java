package delivery.shop.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemGroupId implements Serializable {
    private Long product;
    private Long itemGroup;
}
