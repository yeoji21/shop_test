package delivery.shop;

import com.querydsl.jpa.impl.JPAQueryFactory;
import delivery.common.domain.Money;
import delivery.shop.config.JpaQueryFactoryConfig;
import delivery.shop.shop.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static delivery.shop.shop.QDeliveryFee.deliveryFee;
import static delivery.shop.shop.QShop.shop;

@Transactional
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(JpaQueryFactoryConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShopPersisTest {
    @Autowired
    private JPAQueryFactory queryFactory;
    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void setUp() {
        clear();
    }

    @Test
    void setData() throws Exception{
        for (int i = 0; i < 100; i++) {
            List<Menu> menuList = List.of(new Menu("xx"), new Menu("yy"), new Menu("zz"));
            Shop newShop = Shop.builder()
                    .shopName("shop" + i)
                    .location(new ShopLocation("xxxx-xxxx-xxxx", 1.0, 2.0))
                    .build();
            em.persist(newShop);
        }
    }

    @Test @Rollback(value = false)
    void shop_persist() throws Exception{
        Shop newShop = Shop.builder()
                .shopName("shop")
                .minOrderPrice(new Money(15_000))
                .location(new ShopLocation("xxxx-xxxx-xxxx", 1.0, 2.0))
                .build();

        newShop.addDeliveryFee(new DeliveryFee(new Money(15_000), new Money(3000)));
        newShop.addDeliveryFee(new DeliveryFee(new Money(20_000), new Money(1000)));
        newShop.addDeliveryFee(new DeliveryFee(new Money(25_000), new Money(0)));

        em.persist(newShop);

        clear();

        Shop findShop = queryFactory.selectFrom(shop)
                .where(shop.id.eq(newShop.getId()))
                .fetchOne();

        System.out.println(findShop.getDeliveryFees().size());

        findShop.getDeliveryFees()
                .forEach(d -> System.out.println(d.getOrderPrice() + " -> " + d.getFee()));
    }

    @Test
    void shop_select() throws Exception{
        Shop findShop = queryFactory.selectFrom(shop)
                .where(shop.id.eq(1L))
                .fetchOne();

        System.out.println(findShop.getShopName());
    }


    private void clear() {
        em.clear();
        em.flush();
    }
}
