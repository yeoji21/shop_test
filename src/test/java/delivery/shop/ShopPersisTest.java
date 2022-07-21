package delivery.shop;

import com.querydsl.jpa.impl.JPAQueryFactory;
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

import static delivery.shop.shop.QLocation.location;
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
                    .phoneNumber("052-xxx-xxxx")
                    .introduction("hello~")
                    .location(new Location("xxxx-xxxx-xxxx", 1.0, 2.0))
                    .menuList(menuList)
                    .build();
            em.persist(newShop);
        }
    }

    @Test
    void shop_persist() throws Exception{
        Shop newShop = Shop.builder()
                .shopName("shop")
                .phoneNumber("052-xxx-xxxx")
                .introduction("hello~")
                .location(new Location("xxxx-xxxx-xxxx", 1.0, 2.0))
                .build();
        em.persist(newShop);
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
