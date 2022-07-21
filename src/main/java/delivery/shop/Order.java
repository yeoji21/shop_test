package delivery.shop;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class Order {
    private long productId;
    private List<Items> items;
//    Map<Long, List<Long>> items;

    public Order(long productId, List<Items> items) {
        this.productId = productId;
        this.items = items;
    }

//    public Order(long productId, Map<Long, List<Long>> items) {
//        this.productId = productId;
//        this.items = items;
//    }
}

@Getter
class Items{
    private Long groupId;
    private List<Long> itemIds;

    public Items(Long groupId, List<Long> itemIds) {
        this.groupId = groupId;
        this.itemIds = itemIds;
    }
}