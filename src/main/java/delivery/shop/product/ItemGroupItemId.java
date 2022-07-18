package delivery.shop.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemGroupItemId implements Serializable {
    private Long itemGroup;
    private Long item;
}
