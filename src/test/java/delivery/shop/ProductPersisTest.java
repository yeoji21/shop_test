package delivery.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import delivery.shop.config.JpaQueryFactoryConfig;
import delivery.shop.product.*;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.io.Serializable;

import static delivery.shop.product.QProduct.product;

@Transactional
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(JpaQueryFactoryConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages={"com.shlee.toy1.common.components"})
public class ProductPersisTest {
    @Autowired private JPAQueryFactory queryFactory;
    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void setUp() {
        clear();
    }

    @Test
    void persist() throws Exception{
        Product pizza = Product.builder()
                .name("맛있는 피자")
                .price(15_000)
                .stock(new Stock(100))
                .build();
        em.persist(pizza);

        ItemGroup crust = ItemGroup.builder()
                .name("크러스트 선택")
                .minimumSelectCount(1)
                .maximumSelectCount(1)
                .build();
        em.persist(crust);

        Item cheeseCrust = Item.builder()
                .name("치즈 크러스트")
                .price(2_000)
                .stock(new Stock(30))
                .build();
        em.persist(cheeseCrust);

        Item sweetPotatoCrust = Item.builder()
                .name("고구마 크러스트")
                .price(3_000)
                .stock(new Stock(50))
                .build();
        em.persist(sweetPotatoCrust);

        Item sweetPotatoCheeseCrust = Item.builder()
                .name("고구마 치즈 크러스트")
                .price(4_000)
                .stock(new Stock(20))
                .build();
        em.persist(sweetPotatoCheeseCrust);

        em.persist(new ItemGroupItem(crust, cheeseCrust));
        em.persist(new ItemGroupItem(crust, sweetPotatoCrust));
        em.persist(new ItemGroupItem(crust, sweetPotatoCheeseCrust));

        em.persist(new ProductItemGroup(pizza, crust));
    }


    private void clear() {
        em.clear();
        em.flush();
    }
}
