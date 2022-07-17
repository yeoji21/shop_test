package delivery.shop;

import com.querydsl.jpa.impl.JPAQueryFactory;
import delivery.shop.config.JpaQueryFactoryConfig;
import delivery.shop.shop.Location;
import delivery.shop.shop.QLocation;
import delivery.shop.shop.QShop;
import delivery.shop.shop.Shop;
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

    @Test @Rollback(value = false)
    void shop_persist() throws Exception{
        Shop newShop = Shop.builder()
                .shopName("shop")
                .phoneNumber("052-xxx-xxxx")
                .introduction("hello~")
                .location(Location.builder().streetAddress("xxxx-xxxx-xxxx").build())
                .build();

//        newShop.setLocation(Location.builder().streetAddress("xxxx-xxxx-xxxx").build());
//        Location location = new Location(newShop,"xxxx-xxxx-xxxx");
//        newShop.setLocation(location);

//        em.persist(newShop);
//        em.persist(location);

        clear();

        Shop findShop = queryFactory.selectFrom(shop)
                .join(shop.location, location).fetchJoin()
                .where(shop.id.eq(1L))
                .fetchOne();

        System.out.println(findShop.getShopName());
    }

    private void clear() {
        em.clear();
        em.flush();
    }
}
