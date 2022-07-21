package delivery.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
class OrderTest {

    protected ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void test() throws Exception{
        List<Items> list = new ArrayList<>();
        list.add(new Items(1L, List.of(1L, 3L, 5L)));
        list.add(new Items(2L, List.of(2L)));

//        Map<Long, List<Long>> map = new HashMap<>();
//        map.put(1L, List.of(1L, 3L, 5L));
//        map.put(2L, List.of(2L));

        Order order = new Order(1L, list);
        String json = objectMapper.writeValueAsString(order);
        System.out.println(json);
    }
}